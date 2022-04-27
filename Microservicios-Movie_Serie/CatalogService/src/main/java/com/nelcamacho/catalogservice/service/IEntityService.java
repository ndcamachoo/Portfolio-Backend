package com.nelcamacho.catalogservice.service;

import java.util.Optional;

public interface IEntityService <T> {

    /* ================ Methods ================ */
    Optional<T> getById(String id);
    T getByGenre(String genre);
    T upsert(T entity);

}
