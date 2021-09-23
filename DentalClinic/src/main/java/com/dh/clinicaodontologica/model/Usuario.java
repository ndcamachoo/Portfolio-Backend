package com.dh.clinicaodontologica.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario{

    /* =============== Atributos =============*/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence")

    private Long id;
    private String nombre;
    private String apellido;

    //@Column(unique = true)
    @NotNull
    @Size(min = 2, max = 10, message = "El usuario debe contener entre 2 y 10 caracteres")
    private String user;
    @NotNull
    @Size(min = 2, max = 10, message = "La contraseña debe contener entre 2 y 10 caracteres")
    private String password;

    /* =============== Getters y Setters =============*/

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String usuario) {
        this.user = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* =============== Métodos =============*/

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /* =============== Constructor =============*/

    public Usuario(String nombre, String apellido, String user, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.password = password;
    }

    public Usuario() {
    }
}