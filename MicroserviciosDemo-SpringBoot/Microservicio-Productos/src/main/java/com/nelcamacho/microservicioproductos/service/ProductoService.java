package com.nelcamacho.microservicioproductos.service;

import com.nelcamacho.microserviciocommons.models.Producto; // Utilizando el modelo de producto de commons
import com.nelcamacho.microservicioproductos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IEntityService<Producto> {

    /* =================== Atributos =================== */

    private final ProductoRepository productoRepository;

    @Value("${server.port}")
    private Integer port; // Se puede obtener el valor del port desde el archivo application.properties directamente. // Cabe aclarar que toma el valor de la variable si está automatizada el valor siempre será 0. Para ver el puerto que toma realmente la mejor opción es usar Environment.

    // private final Environment environment; // Se utiliza la clase Environment que contiene las variables definidas en el application.properties y de alli, obtiene el valor de la variable. (En este caso server.port para obtener el puerto del servicio).


    /* =================== Métodos =================== */

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        // Método 1: Environment
        //return productoRepository.findAll().stream().peek(p -> p.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))))).collect(Collectors.toList());

        // Método 2: Value
        return productoRepository.findAll().stream().peek(p -> p.setPort(port)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {

        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {

            // Método 1: Environment
            // producto.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));

            // Método 2: Value
            producto.setPort(port);

            return producto;
        }
        return null;
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        producto.setCreateAt(LocalDate.now());

        // Método 1: Environment
        // producto.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));

        // Método 2: Value
        producto.setPort(port);

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto update(Producto producto) {

        Producto productoActual = productoRepository.findById(producto.getId()).orElse(null);

        if (productoActual != null) {

            productoActual.setNombre(producto.getNombre());
            productoActual.setPrecio(producto.getPrecio());

            return productoRepository.save(productoActual);
        }

        return null;
    }

    @Override
    @Transactional
    public String delete(Long id) {

        if(productoRepository.findById(id).isPresent()) {
            productoRepository.deleteById(id);
            return "Producto ID:" + id + " eliminado";
        }

        return "Producto no encontrado";
    }

    /* =================== Constructores =================== */

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
}
