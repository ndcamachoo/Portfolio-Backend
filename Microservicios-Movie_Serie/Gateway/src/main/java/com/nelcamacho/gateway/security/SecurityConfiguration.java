package com.nelcamacho.gateway.security;

import com.nelcamacho.gateway.filters.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfiguration {

    /* ================ Atributos ================ */

    private final JwtAuthenticationFilter authenticationFilter;

    /* ================ Métodos ================ */

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {

        return http.authorizeExchange()
                .pathMatchers("/security/oauth/**").permitAll()
                .pathMatchers("/users/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.GET).permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();

    }

    /* ================ Constructores ================ */

    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }
}
