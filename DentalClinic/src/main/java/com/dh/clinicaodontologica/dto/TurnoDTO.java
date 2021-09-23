package com.dh.clinicaodontologica.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoDTO implements Serializable {

    /* =============== Atributos ============= */

    private Long id;
    private String nombrePaciente;
    private String nombreOdontologo;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;

    /* =============== Getters y Setters ============= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreOdontologo() {
        return nombreOdontologo;
    }

    public void setNombreOdontologo(String nombreOdontologo) {
        this.nombreOdontologo = nombreOdontologo;
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
    /* =============== Constructor ============= */

    public TurnoDTO(String nombrePaciente, String nombreOdontologo, LocalDate fechaTurno, LocalTime horaTurno) {
        this.nombrePaciente = nombrePaciente;
        this.nombreOdontologo = nombreOdontologo;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
    }

    public TurnoDTO() {
    }
}
