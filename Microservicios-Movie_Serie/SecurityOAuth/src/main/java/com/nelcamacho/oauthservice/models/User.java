package com.nelcamacho.oauthservice.models;

import java.util.List;

public class User {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private Boolean enabled;
    private List<Role> roles;
    private Integer intentos;

    /* ================= Getters & Setters ================ */

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String apellido) {
        this.lastname = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    /* ================= Métodos ================ */
    /* ================= Constructores ================ */

    public User(String username, String password, String name, String lastname, String email, Boolean enabled, List<Role> roles, Integer intentos) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
        this.intentos = intentos;
    }

    public User() {
    }

}
