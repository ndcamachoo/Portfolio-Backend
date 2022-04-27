package com.nelcamacho.userservice.config;

import com.nelcamacho.userservice.models.Role;
import com.nelcamacho.userservice.models.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfiguration implements RepositoryRestConfigurer{

    //RepositoryRestConfigurer es una interfaz que nos permite configurar los repositorios de Spring Data Rest

    /* ====================== Métodos ====================== */

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        config.exposeIdsFor(User.class, Role.class); //Exponemos los ids de los objetos de la base de datos
        //RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
    }

}
