package com.dh.clinicaodontologica.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    /* ================ Atributos ================= */

    private Long id;
    private String fullname;
    private String user;

    /* ================ Getters y Setters ================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /* ================ Métodos ================= */

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    /* ================ Constructor ================= */

    public UsuarioDTO(String fullname, String user) {
        this.fullname = fullname;
        this.user = user;
    }

    public UsuarioDTO() {
    }
}
