package com.nelcamacho.microservicioproductos.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    /* ========================= Atributos ========================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;
    private String nombre;
    private Double precio;

    @Column(name = "create_at")
    // @Temporal(TemporalType.DATE)// Para que el campo sea de tipo fecha y se guarde en un formato correcto (sql.date)
    private LocalDate createAt;

    @Transient // Este dato no se guarda en la tabla productos
    private Integer port;

    /* ========================= Getters y Setters ======================= */

    public Long getId() {
        return id;
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

    public Producto(String nombre, Double precio, LocalDate createAt) {
        this.nombre = nombre;
        this.precio = precio;
        this.createAt = createAt;
    }

    public Producto() {
    }

}

// NO SE USA -> Modelo implementa Commons