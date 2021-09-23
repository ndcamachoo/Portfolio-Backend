package com.dh.clinicaodontologica.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "domicilios")
public class Domicilio {

    /* =============== Atributos =============*/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
    @SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence")

    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    /* =============== Getters y Setters =============*/

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /* =============== Métodos =============*/

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }

    /* =============== Constructor =============*/

    public Domicilio(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio() {
    }
}