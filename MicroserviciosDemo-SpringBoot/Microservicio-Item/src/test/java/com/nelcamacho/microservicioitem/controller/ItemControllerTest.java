package com.nelcamacho.microservicioitem.controller;

import com.nelcamacho.microservicioitem.models.Item;
import com.nelcamacho.microservicioitem.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemControllerTest {

    /* ============= Atributos ============= */

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @Mock
    Item item;

    private static final Long ID = 1L;

    /* ============= Test ============= */

    @Test
    public void testFindAll() {
        List<Item> items = new ArrayList<>();
        items.add(item);
        when(itemService.findAll()).thenReturn(items);
        assertEquals(itemController.getAllItems(null,null).getBody(), items);
    }

    @Test
    public void testFindById() {
        when(itemService.findById(ID, 1)).thenReturn(item);
        assertEquals(itemController.getItemById(ID, 1).getBody(), item);
    }

}
