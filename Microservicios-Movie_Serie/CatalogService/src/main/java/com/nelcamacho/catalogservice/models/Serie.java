package com.nelcamacho.catalogservice.models;

import lombok.Data;

import java.util.List;

@Data
public class Serie {

    /* =================== Attributes =================== */

    private Long id;
    private String name;
    private String genre;
    private List<Season> seasons;

}
