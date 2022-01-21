package com.nelcamacho.microservicioproductos.controller;

import com.nelcamacho.microserviciocommons.models.Producto; // Utilizando el modelo de producto de commons
import com.nelcamacho.microservicioproductos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductoController {

    /* ================== Atributos ================== */

    private final ProductoService productoService;

    /* ================== Métodos ================== */
    /* ================== GET ================== */

    @GetMapping()
    public List<Producto> findAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductoById(@PathVariable Long id) {

        Producto producto = productoService.findById(id);

        // Simulación de error (Si retorna un error, Resilience4J se encarga de manejarlo)
        if(id == 6){
            throw new RuntimeException("Simulación de error para el caso de fallo");
        }

        if (id == 2) {
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (producto == null) {


            /*
            boolean ok = false;
            if(!ok){
                throw new RuntimeException("Simulación de error para Hystrix/Resilience4J");
                throw new IllegalStateException("Simulación de error para Hystrix/Resilience4J");
            }
            */


            // Simulación de timeout (Si la petición tiene un retraso considerable (Si excede un minuto retornará una excepción), Hystrix se encarga de manejarlo)
            /*try {
                Thread.sleep(2000L);
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto con id " + id + " no existe");
        }

        return ResponseEntity.ok(producto);

    }

    /* ================== POST ================== */

    @PostMapping("/save")
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    /* ================== PUT ================== */

    @PutMapping("/update")
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {

        Producto productoActual = productoService.findById(producto.getId());
        if (productoActual == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto con id " + producto.getId() + " no existe");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productoService.update(producto));
    }

    /* ================== DELETE ================== */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto con id " + id + " no existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productoService.delete(id));
    }

    /* ================== Constructores ================== */

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
}
