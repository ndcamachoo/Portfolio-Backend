package com.nelcamacho.microserviciousuarios.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    /* ================= Atributos ================ */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username; // Nombre de usuario → debe ser único

    @Column(length = 60)
    private String password; // Contraseña del usuario

    private String nombre; // Nombre del usuario
    private String apellido; // Apellido del usuario

    @Column(unique = true, length = 100)
    private String email; // Email del usuario → debe ser único

    private Boolean enabled; // Indica si el usuario está activo o no

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
    private List<Role> roles;

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

    /* ================= Métodos ================ */
    /* ================= Constructores ================ */

    public Usuario(String username, String password, String nombre, String apellido, String email, Boolean enabled, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Usuario() {
    }
}
