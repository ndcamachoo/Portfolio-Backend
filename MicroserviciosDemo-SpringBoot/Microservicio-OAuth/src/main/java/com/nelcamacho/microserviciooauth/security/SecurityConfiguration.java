package com.nelcamacho.microserviciooauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { //WebSecurityConfigurerAdapter es una clase que permite configurar la seguridad de la aplicación.

    /* =================== Atributos =================== */

    private final UserDetailsService usuarioService; // Servicio de usuarios de Spring Security implementado desde el servicio de usuarios.
    private final AuthenticationEventPublisher authenticationEventPublisher; // Servicio de publicación de eventos de autenticación.

    /* =================== Métodos =================== */

    @Bean // Se indica que el método es un bean para almacenarlo en el contenedor de Spring.
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Se crea una instancia de la clase BCryptPasswordEncoder para encriptar.
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Configuración de la autenticación de usuarios.
        auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder()) // Se indica el servicio de usuarios de Spring Security.
                .and().authenticationEventPublisher(this.authenticationEventPublisher); // Se indica el servicio de publicación de eventos de autenticación.
    }

    @Bean // Se indica que el método es un bean para almacenarlo en el contenedor de Spring.
    @Override
    protected AuthenticationManager authenticationManager() throws Exception { // Gestión de la autenticación de usuarios.
        return super.authenticationManager();
    }

    /* =================== Constructores =================== */

    @Autowired
    public SecurityConfiguration(UserDetailsService usuarioService, AuthenticationEventPublisher authenticationEventPublisher) {
        this.usuarioService = usuarioService;
        this.authenticationEventPublisher = authenticationEventPublisher;
    }

    public SecurityConfiguration(boolean disableDefaults, UserDetailsService usuarioService, AuthenticationEventPublisher authenticationEventPublisher) {
        super(disableDefaults);
        this.usuarioService = usuarioService;
        this.authenticationEventPublisher = authenticationEventPublisher;
    }
}
