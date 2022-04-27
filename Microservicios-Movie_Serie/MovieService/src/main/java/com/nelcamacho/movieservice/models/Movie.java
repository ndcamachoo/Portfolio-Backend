package com.nelcamacho.movieservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    /* =================== Attributes =================== */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

}
