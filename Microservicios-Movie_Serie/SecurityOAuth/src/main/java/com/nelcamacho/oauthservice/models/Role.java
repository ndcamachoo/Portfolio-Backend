package com.nelcamacho.oauthservice.models;

public class Role {

    /* ================= Atributos ================ */

    private Long id;
    private String name;

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
