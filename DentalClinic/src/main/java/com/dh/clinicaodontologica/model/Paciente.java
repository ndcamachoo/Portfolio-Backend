package com.dh.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Paciente {

    /* =============== Atributos =============*/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence")

    private Long id;
    private Integer DNI;
    private LocalDate fechaIngreso;

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

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    /* =============== Métodos =============*/

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", DNI=" + DNI +
                ", fechaIngreso=" + fechaIngreso +
                ", usuario=" + usuario +
                ", domicilio=" + domicilio +
                '}';
    }

    /* =============== Constructor =============*/

    public Paciente(Integer DNI, LocalDate fechaIngreso, Usuario usuario, Domicilio domicilio) {
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.usuario = usuario;
        this.domicilio = domicilio;
    }

    public Paciente() {
    }
}