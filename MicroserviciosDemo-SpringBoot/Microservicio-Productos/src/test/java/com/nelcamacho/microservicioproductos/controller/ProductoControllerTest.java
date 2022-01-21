package com.nelcamacho.microservicioproductos.controller;

import com.nelcamacho.microserviciocommons.models.Producto; // Utilizando el modelo de producto de commons
import com.nelcamacho.microservicioproductos.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductoControllerTest {

    /* ============= Atributos ============= */

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    @Mock
    Producto producto;

    private static final Long ID = 1L;

    /* ============= Test ============= */

    @Test
    public void testFindAll() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        when(productoService.findAll()).thenReturn(productos);
        assertEquals(productoController.findAllProductos().size(), productos.size());
    }

    @Test
    public void testFindById() {
        when(productoService.findById(ID)).thenReturn(producto);
        assertEquals(productoController.findProductoById(ID).getBody(), producto);
    }

    @Test
    public void testFindByIdNull() {
        when(productoService.findById(ID)).thenReturn(null);
        assertEquals(productoController.findProductoById(ID).getBody(), "El producto con id 1 no existe");
    }

    @Test
    public void testSave() {
        when(producto.getId()).thenReturn(ID);
        when(producto.getNombre()).thenReturn("Test name");
        when(producto.getPrecio()).thenReturn(2000.0);
        when(producto.getCreateAt()).thenReturn(LocalDate.now());
        when(productoService.save(producto)).thenReturn(producto);

        ResponseEntity<?> responseEntity = productoController.saveProducto(producto);
        assertEquals(productoController.saveProducto(producto).getStatusCode(), HttpStatus.CREATED);
        assertEquals(responseEntity.getBody(), producto);
    }

    @Test
    public void testUpdate() {
        when(producto.getId()).thenReturn(ID);
        when(producto.getNombre()).thenReturn("Test name");
        when(producto.getPrecio()).thenReturn(5000.0);
        when(producto.getCreateAt()).thenReturn(LocalDate.now());
        when(productoService.findById(ID)).thenReturn(producto);

        ResponseEntity<?> responseEntity = productoController.updateProducto(producto);
        assertEquals(productoController.updateProducto(producto).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateNull() {
        when(producto.getId()).thenReturn(ID);
        when(productoService.findById(ID)).thenReturn(null);

        ResponseEntity<?> responseEntity = productoController.updateProducto(producto);
        assertEquals(productoController.updateProducto(producto).getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(responseEntity.getBody(), "El producto con id 1 no existe");

    }

    @Test
    public void testDelete() {
        when(productoService.findById(ID)).thenReturn(producto);
        assertEquals(productoController.deleteProducto(ID).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteNull() {
        when(productoService.findById(ID)).thenReturn(null);
        assertEquals(productoController.deleteProducto(ID).getStatusCode(), HttpStatus.NOT_FOUND);
    }


}
