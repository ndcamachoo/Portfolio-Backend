package com.nelcamacho.microservicioitem.service;

import com.nelcamacho.microservicioitem.models.Item;
import com.nelcamacho.microserviciocommons.models.Producto; // Importamos el modelo de producto de commons
import com.nelcamacho.microservicioitem.repository.ProductoClienteRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService implements IEntityService<Item> {

    /* ===================== Atributos ===================== */

    // // Método 1: RestTemplate
    //private final RestTemplate clientRest;

    // Método 2: Feign
    private final ProductoClienteRest clientFeign;

    /* ===================== Métodos ===================== */

    @Override
    public List<Item> findAll(){

        // // Método 1: RestTemplate

        /* List<Producto> productos = Arrays.asList(Objects.requireNonNull(clientRest.getForObject("http://localhost:8001/productos", Producto[].class))); // Permite hacer una petición GET a una URL y obtener una respuesta en formato JSON (En este caso una lista de Productos)
        // Transformar una lista de Productos en una lista de Items
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());*/

        // // Método 2: Feign
        return clientFeign.findAll().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());

    }

    @Override
    public Item findById(Long id, Integer cantidad){

        // // Método 1: RestTemplate

        /*Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = clientRest.getForObject("http://localhost:8001/productos/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);*/

        // // Método 2: Feign
        return new Item(clientFeign.findById(id), cantidad);

    }

    @Override
    public Item save(Producto producto) {

        // // Método 1: RestTemplate
        /*HttpEntity<Producto> body = new HttpEntity<Producto>(producto)
          ResponseEntity<?> response = clientRest.exchange("http://servicio-productos/save", HttpMethod.POST, body, Producto.class);
          Producto productoResponse = response.getBody();

          return new Item(productoResponse, 1);*/

        // // Método 2: Feign
        return new Item(clientFeign.save(producto), 1);

    }

    @Override
    public Item update(Producto producto) {

        // // Método 1: RestTemplate
        /*HttpEntity<Producto> body = new HttpEntity<Producto>(producto)
          ResponseEntity<?> response = clientRest.exchange("http://servicio-productos/update", HttpMethod.PUT, body, Producto.class);
          Producto productoResponse = response.getBody();

          return new Item(productoResponse, 1);*/

        // // Método 2: Feign
        return new Item(clientFeign.update(producto), 1);

    }

    @Override
    public String delete(Long id) {

        // // Método 1: RestTemplate
        /*Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        clientRest.delete("http://servicio-productos/delete/{id}", pathVariables);

        return "Producto eliminado";*/

        // // Método 2: Feign
        return clientFeign.delete(id);

    }

    /* ===================== Constructores ===================== */

    @Autowired
    // @Qualifier("productoClienteRest") // Se puede especificar el nombre del bean que se va a inyectar en el constructor de la clase (Dato el caso que exista más de un bean con el mismo nombre o con la misma implementación)
    public ItemService(ProductoClienteRest clientFeign) {
        this.clientFeign = clientFeign;
    }

}
