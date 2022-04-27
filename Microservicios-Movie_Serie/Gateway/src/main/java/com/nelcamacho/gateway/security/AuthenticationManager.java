package com.nelcamacho.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    /* ================ Atributos ================ */

    private final Environment env;

    /* ================ Métodos ================ */

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        // Si el token ha expirado se lanza una excepción.
        // Se retorna un error.
        return Mono.just(authentication.getCredentials().toString()) // Se obtiene el token del usuario en un Mono.
                .map(token -> {
                    SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(Objects.requireNonNull(env.getProperty("config.security.oauth.jwt.key")).getBytes())); // Se obtiene la clave secreta del token en base64 en función de la Key alojada en el servidor de configuración.
                    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody(); // Se obtienen los datos del token (claims).
                }).onErrorResume(ExpiredJwtException.class, Mono::error) // Si el token ha expirado se lanza una excepción.
                .map(claims -> {
                    String username = claims.get("user_name", String.class); // Se obtiene el nombre de usuario del token.
                    List<String> roles = claims.get("authorities", List.class); // Se obtienen los roles del token.
                    List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList(); // Se obtienen los roles del token en forma de Collection..
                    return new UsernamePasswordAuthenticationToken(username, null, authorities); // Se crea una autenticación de usuario con los datos obtenidos.
                });
    }

    /* ================ Constructores ================ */

    @Autowired
    public AuthenticationManager(Environment env) {
        this.env = env;
    }
}
