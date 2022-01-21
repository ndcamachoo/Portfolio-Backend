package com.nelcamacho.microservicioitem.controller;
import com.nelcamacho.microservicioitem.models.Item;
import com.nelcamacho.microserviciocommons.models.Producto; // Importamos el modelo de producto de commons
import com.nelcamacho.microservicioitem.service.ItemService;

import feign.RetryableException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@RestController
public class ItemController {

    /* ================= Atributos ==================== */

    private final Logger log = LoggerFactory.getLogger(ItemController.class);
    private final Environment env;
    private final ItemService itemService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    /* ================= Métodos ==================== */
    /* ================= GET ======================== */

    // Obtener una respuesta de los parametros de configuración usando Value (Apuntando al servidor de configuración)
    @GetMapping("/config")
    public ResponseEntity<?> getConfig(@Value("${configuracion.text}") String texto, @Value("${server.port}") String puerto) {

        log.info(texto);
        log.info(puerto);

        Map<String, String> config = new HashMap<>();
        config.put("texto", texto);
        config.put("puerto", puerto);

        if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            config.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            config.put("autor.email", env.getProperty("configuracion.autor.email"));
        }

        return new ResponseEntity<>(config, HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<?> getAllItems(@RequestParam(name="nombre", required = false) String nombre, @RequestHeader(name="token-request", required = false) String token) {

        // // Método 1: RestTemplate

        /*try{
            return ResponseEntity.ok(itemService.findAll());
        }catch (RestClientException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener los items -> No hay conexión con el servicio de productos");
        }*/

        // // Método 2: Feign

        try{
            System.out.println("Nombre: " + nombre);
            System.out.println("Token-Request: " + token);
            return ResponseEntity.ok(itemService.findAll());
        }catch (RetryableException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener los items -> No hay conexión con el servicio de productos");
        }
    }

    @GetMapping("/{id}/cantidad/{cantidad}")
    //@HystrixCommand(fallbackMethod = "metodoAlternativo") // Permite definir un método alternativo para el caso de fallo
    public ResponseEntity<?> getItemById(@PathVariable Long id, @PathVariable Integer cantidad) {

        // // Método 1: RestTemplate

        /*try{
            return ResponseEntity.ok(itemService.findById(id, cantidad));
        }catch (RestClientException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener el item -> No hay conexión con el servicio de productos");
        }*/

        // // Método 2: Feign

        /*try{
            return ResponseEntity.ok(itemService.findById(id, cantidad));

        }catch (RetryableException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener el item -> No hay conexión con el servicio de productos");
        }*/

        // Resiliencia:
        return circuitBreakerFactory.create("items").run(() -> ResponseEntity.ok(itemService.findById(id, cantidad)), e -> metodoAlternativo(id, cantidad, e)); // Si se produce una falla, se abre el circuito. El segundo parametro de Run es el método alternativo en caso de fallo.
    }

    // Segundo metodo para implementación de Circuit Breaker mediante anotaciones @CircuitBreaker
    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo") // Nombre del circuito (La configuración que se aplica es bajo .properties o .yml, pero no sirve desde la configuración con la anotación @Configuration)
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public ResponseEntity<?> getItem2ById(@PathVariable Long id, @PathVariable Integer cantidad) {

        return ResponseEntity.ok(itemService.findById(id, cantidad));
    }

    // Tercer metodo para implementación de Circuit Breaker mediante anotaciones @TimeLimiter
    //@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2") // Si se quiere combinar con la anotación @CircuitBreaker, se implementa de igual manera llamando al método alternativo con las características de @TimeLimiter
    @TimeLimiter(name = "items", fallbackMethod = "metodoAlternativo2") // La anotación @TimeLimiter permite definir un tiempo máximo de ejecución del método. Si se supera el tiempo máximo, se abre el circuito.
    @GetMapping("/ver2/{id}/cantidad/{cantidad}")
    public CompletableFuture<ResponseEntity<?>> getItem3ById(@PathVariable Long id, @PathVariable Integer cantidad) {

        // Es necesario envolver el método en una función que permita controlar el tiempo de ejecución para asi determinar si se abre el circuito o no.
        // Se implementa con la clase CompletableFuture que permite controlar el tiempo de ejecución de un método a través de concurrencia.
        // CompletableFuture permite ejecutar un método asíncronamente.

        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(itemService.findById(id, cantidad)));
    }


    // Método alternativo para el caso de fallo
    public ResponseEntity<?> metodoAlternativo(Long id, Integer cantidad, Throwable e) {

        Producto producto = new Producto();
        //producto.setId(id);
        producto.setNombre("Producto no encontrado");
        producto.setPrecio(0.0);
        log.error(e.getMessage());

        return ResponseEntity.ok(new Item(producto, cantidad));
    }

    // Método alternativo para el caso de fallo mediante la anotación @TimeLimiter
    // El método alternativo debe ser de tipo CompletableFuture para poder ser utilizado por el método @TimeLimiter
    public CompletableFuture<ResponseEntity<?>> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {

        Producto producto = new Producto();
        //producto.setId(id);
        producto.setNombre("Producto no encontrado");
        producto.setPrecio(0.0);
        log.error(e.getMessage());

        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(new Item(producto, cantidad)));
    }

    /* ================= Constructor ==================== */

    @Autowired
    public ItemController(Environment env, ItemService itemService, CircuitBreakerFactory circuitBreakerFactory) {
        this.env = env;
        this.itemService = itemService;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }
}
