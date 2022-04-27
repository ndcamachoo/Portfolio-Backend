package com.nelcamacho.serieservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "series")
public class Serie {

    /* =================== Attributes =================== */
    @Id
    private Long id;
    private String name;
    private String genre;
    private List<Season> seasons;

    @Transient
    public static final String SEQUENCE_NAME = "serie_sequence";

}
