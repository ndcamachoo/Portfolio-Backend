package com.nelcamacho.microserviciogateway.security;

import com.nelcamacho.microserviciogateway.filters.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity // Habilita la seguridad en el proyecto basado en programación reactiva (WebFlux)
public class SecurityConfiguration {

    /* ================ Atributos ================ */

    private final JwtAuthenticationFilter authenticationFilter;

    /* ================ Métodos ================ */

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {

        return http.authorizeExchange() // Autoriza las peticiones
                .pathMatchers("/security/oauth/**").permitAll() // Permite todas las peticiones a la ruta de seguridad
                .pathMatchers(HttpMethod.GET, "/productos", "/productos/{id}" ,"/items", "/items/{id}/cantidad/{cantidad}", "/usuarios/usuarios").permitAll() // Permite todas las peticiones a la ruta de productos, items y usuarios
                .pathMatchers(HttpMethod.GET, "/usuarios/usuarios/{id}").hasAnyRole("ADMIN","USER") // Permite la petición a la ruta de usuarios con el rol de ADMIN o USER
                .pathMatchers("/productos/**", "/items/**", "/usuarios/usuarios/**").hasRole("ADMIN") // Permite la petición a la ruta de productos, items y usuarios con los métodos diferentes a GET con el rol de ADMIN
                .anyExchange().authenticated() // Cualquier petición debe ser autenticada
                .and()
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Añade el filtro de autenticación
                .csrf().disable() // Deshabilita el CSRF (Cross-Site Request Forgery)
                .build(); // Construye el filtro de seguridad

    }

    /* ================ Constructores ================ */

    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }
}
