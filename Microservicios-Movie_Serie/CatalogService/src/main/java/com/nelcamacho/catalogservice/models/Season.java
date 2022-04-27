package com.nelcamacho.catalogservice.models;

import lombok.Data;

import java.util.List;

@Data
public class Season {

    /* ================== Attributes ================== */
    private Long id;
    private Integer seasonNumber;
    private List<Chapter> chapters;
}
