package com.nelcamacho.microservicioproductos.service;

import java.util.List;

public interface IEntityService <T>{

    /* =================== Métodos ======================= */

    List<T> findAll();
    T findById(Long id);
    T save(T t);
    T update(T t);
    String delete(Long id);

}
