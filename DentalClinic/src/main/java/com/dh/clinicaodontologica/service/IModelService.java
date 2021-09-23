package com.dh.clinicaodontologica.service;

import java.util.List;
import java.util.Optional;

public interface IModelService<T> {

    /* ================= Métodos ================= */

    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T t);
    String delete(Long id);
    String update(T t);

}
