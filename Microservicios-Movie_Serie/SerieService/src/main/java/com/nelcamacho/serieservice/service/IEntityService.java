package com.nelcamacho.serieservice.service;

import java.util.List;

public interface IEntityService <T> {

    /* ================= Methods ================= */
    List<T> getAll();
    T getById(Long id);
    T upsert(T entity);
    void delete(Long id);

}
