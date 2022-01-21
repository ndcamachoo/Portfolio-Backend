package com.nelcamacho.microservicioitem.service;

import com.nelcamacho.microserviciocommons.models.Producto; // Importamos el modelo de producto de commons

import java.util.List;

public interface IEntityService <T> {

    /* ==================== Métodos =================== */

    List<T> findAll();
    T findById(Long id, Integer cantidad);
    T save(Producto producto);
    T update(Producto producto);
    String delete(Long id);

}
