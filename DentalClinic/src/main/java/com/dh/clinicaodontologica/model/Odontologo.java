package com.dh.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "odontologos")
public class Odontologo {

    /* =============== Atributos =============*/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence")

    private Long id;
    private Integer numeroMatricula;
    private boolean admin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    /* =============== Getters y Setters =============*/

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(Integer numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /* =============== Métodos =============*/

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", numeroMatricula=" + numeroMatricula +
                ", admin=" + admin +
                ", usuario=" + usuario +
                ", domicilio=" + domicilio +
                '}';
    }

    /* =============== Constructor =============*/

    public Odontologo(Integer numeroMatricula, boolean admin, Usuario usuario, Domicilio domicilio) {
        this.numeroMatricula = numeroMatricula;
        this.admin = admin;
        this.usuario = usuario;
        this.domicilio = domicilio;
    }

    public Odontologo() {
    }
}