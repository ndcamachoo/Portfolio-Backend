package com.nelcamacho.catalogservice.config;

import com.nelcamacho.catalogservice.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class CreateDropRunner {

    /* ===================== Attributes ===================== */

    private final CatalogRepository catalogRepository;

    /* ===================== Methods ===================== */

    // DROP DATA IN DATABASE WHEN APPLICATION IS SHUTDOWN
    @PreDestroy
    public void shutdown() {
        catalogRepository.deleteAll();
    }

    /* ===================== Constructors ===================== */

    @Autowired
    public CreateDropRunner(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }
}