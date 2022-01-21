package com.nelcamacho.microservicioitem.repository;

import com.nelcamacho.microserviciocommons.models.Producto; // Importamos el modelo de producto de commons
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-productos") // Con esta anotación indicamos que esta clase es un cliente de feign, requiere el nombre de la aplicación y su URL
// Implementando Ribbon(Eureka) como load balancer no es necesario indicar el url, ya que se encarga de buscar la url del servicio que se necesita a partir del nombre
public interface ProductoClienteRest {

    // Se crean métodos de la interfaz que alojarán las peticiones a la API del servicio de productos
    /* ================ Métodos =================== */

    @GetMapping("/")// En el feign cliente se indica que el método a invocar es un GET e indicamos el endpoint para el servicio
    List<Producto> findAll();

    @GetMapping("/{id}")
    Producto findById(@PathVariable Long id);

    @PostMapping("/save")
    Producto save(@RequestBody Producto producto);

    @PutMapping("/update")
    Producto update(@RequestBody Producto producto);

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Long id);

}
