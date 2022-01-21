package com.nelcamacho.microserviciogateway.filters.security;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    /* ====================== Atributos ====================== */

    private final ReactiveAuthenticationManager authenticationManager;

    /* ====================== Métodos ====================== */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) { // Se establece el filtro de autenticación
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)) // Se obtiene el token de autenticación del header
                .filter(header -> header.startsWith("Bearer ")) // Se filtra el token para obtener solo el token (sin el Bearer)
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty())) // Si no se encuentra el token, se continúa con el filtro
                .map(token -> token.replace("Bearer ", "")) // Se obtiene el token (sin el Bearer)
                .flatMap(token -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(null, token))) // Se autentica el token
                .flatMap(authentication -> chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))); // Se establece el contexto de seguridad implementando la autenticación
    }

    /* ====================== Constructor ====================== */

    @Autowired
    public JwtAuthenticationFilter(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
