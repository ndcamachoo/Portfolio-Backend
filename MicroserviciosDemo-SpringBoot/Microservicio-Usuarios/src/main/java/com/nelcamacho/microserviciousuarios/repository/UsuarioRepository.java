package com.nelcamacho.microserviciousuarios.repository;

import com.nelcamacho.microserviciousuarioscommons.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource // RepositoryRestResource indica que esta clase es un repositorio para la API REST y define el path general que se usará en la API REST
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @RestResource(path = "username")
    Usuario findByUsername(String username);

}
