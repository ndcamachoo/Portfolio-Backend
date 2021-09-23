package com.dh.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "turnos")
public class Turno {

    /* =============== Atributos ============= */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    private LocalDate fechaTurno;
    private LocalTime horaTurno;

    /* =============== Getters y Setters ============= */

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    /* =============== Métodos ============= */

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechaTurno=" + fechaTurno +
                ", horaTurno=" + horaTurno +
                '}';
    }

    /* =============== Constructor ============= */


    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fechaTurno, LocalTime horaTurno) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
    }

    public Turno() {
    }
}