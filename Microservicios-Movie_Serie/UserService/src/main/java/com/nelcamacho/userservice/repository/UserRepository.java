package com.nelcamacho.userservice.repository;

import com.nelcamacho.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource // RepositoryRestResource indica que esta clase es un repositorio para la API REST y define el path general que se usará en la API REST
public interface UserRepository extends JpaRepository<User, Long> {

    @RestResource(path = "username")
    User findByUsername(String username);

}
