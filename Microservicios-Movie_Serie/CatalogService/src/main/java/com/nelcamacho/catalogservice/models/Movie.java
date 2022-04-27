package com.nelcamacho.catalogservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    /* ============== Attributes ============== */
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

}