package com.nelcamacho.microservicioitem.models;

import java.time.LocalDate;

public class Producto{

    /* ========================= Atributos ========================= */

    private Long id;
    private String nombre;
    private Double precio;
    private LocalDate createAt;
    private Integer port;

    /* ========================= Getters y Setters ======================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /* ========================= Métodos ========================= */

    /* ========================= Constructores ========================= */

    public Producto(Long id, String nombre, Double precio, LocalDate createAt) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.createAt = createAt;
    }

    public Producto() {
    }

}
