package com.nelcamacho.serieservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Chapter {

    /* ================== Attributes ================== */
    @Id
    private Long id;
    private String name;
    private Long number;
    private String urlStream;


    public static final String SEQUENCE_NAME = "chapter_sequence";

}
