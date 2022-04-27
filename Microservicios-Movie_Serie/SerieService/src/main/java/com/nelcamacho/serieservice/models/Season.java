package com.nelcamacho.serieservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class Season {

    /* ================== Attributes ================== */
    @Id
    private Long id;
    private Integer seasonNumber;
    private List<Chapter> chapters;

    public static final String SEQUENCE_NAME = "season_sequence";

}
