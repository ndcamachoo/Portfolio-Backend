package com.nelcamacho.microservicioitem.service;

import com.nelcamacho.microservicioitem.repository.ProductoClienteRest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    /* ============= Atributos ============= */

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ProductoClienteRest productoClienteRest;

    private static final Long ID = 1L;

    /* ============= Test ============= */

    @Test
    public void findAllTest() {
        itemService.findAll();
        verify(productoClienteRest).findAll();
    }

    @Test
    public void findByIdTest() {
        itemService.findById(ID, 1);
        verify(productoClienteRest).findById(ID);
    }

}
