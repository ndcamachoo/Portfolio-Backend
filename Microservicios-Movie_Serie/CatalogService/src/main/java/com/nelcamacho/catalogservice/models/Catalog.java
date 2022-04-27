package com.nelcamacho.catalogservice.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "catalogs")
public class Catalog {

    /* ====================== Attributes ====================== */
    @Id
    private String id;
    private String genre;
    private List<Movie> movies;
    private List<Serie> series;

    @Transient
    public static final String SEQUENCE_NAME = "catalog_sequence";

}
