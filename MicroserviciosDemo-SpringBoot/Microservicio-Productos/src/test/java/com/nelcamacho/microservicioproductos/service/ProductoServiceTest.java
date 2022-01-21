package com.nelcamacho.microservicioproductos.service;

import com.nelcamacho.microserviciocommons.models.Producto; // Utilizando el modelo de producto de commons
import com.nelcamacho.microservicioproductos.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    /* ============= Atributos ============= */

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    private static final Long ID = 1L;

    /* ============= Test ============= */

    @Test
    public void findAllTest() {
        productoService.findAll();
        verify(productoRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        productoService.findById(ID);
        verify(productoRepository).findById(ID);
    }

    @Test
    public void saveTest() {
        Producto producto = mock(Producto.class);
        productoService.save(producto);
        verify(productoRepository).save(producto);
    }

    @Test
    public void updateTest() {
        Producto producto = mock(Producto.class);
        when(producto.getId()).thenReturn(ID);
        when(producto.getNombre()).thenReturn("Test name");
        when(producto.getPrecio()).thenReturn(2000.0);
        when(productoRepository.findById(ID)).thenReturn(Optional.of(producto));
        productoService.update(producto);
        verify(productoRepository).save(producto);
    }

    @Test
    public void updateTestNull() {
        Producto producto = mock(Producto.class);
        when(productoRepository.findById(ID)).thenReturn(Optional.empty());
        productoService.update(producto);
        verify(productoRepository, never()).save(producto);
    }

    @Test
    public void deleteTest() {
        Producto producto = mock(Producto.class);
        when(productoRepository.findById(ID)).thenReturn(Optional.of(producto));
        productoService.delete(ID);
        verify(productoRepository).deleteById(ID);
    }

    @Test
    public void deleteTestNull() {
        when(productoRepository.findById(ID)).thenReturn(Optional.empty());
        productoService.delete(ID);
        verify(productoRepository, never()).deleteById(ID);
    }

}
