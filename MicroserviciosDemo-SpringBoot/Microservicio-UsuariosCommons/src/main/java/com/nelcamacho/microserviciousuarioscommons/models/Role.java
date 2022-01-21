package com.nelcamacho.microserviciousuarioscommons.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    /* ================= Atributos ================ */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String name; // Nombre del rol

    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") // Relación bidireccional
    //private List<Usuario> usuarios; // Usuarios que tienen este rol

    /* ================= Getters & Setters ================ */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* ================= Métodos ================ */
    /* ================= Constructores ================ */

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

}
