package com.nelcamacho.microserviciooauth.services;

import brave.Tracer;
import com.nelcamacho.microserviciooauth.client.UsuarioClient;
import com.nelcamacho.microserviciousuarioscommons.models.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, IEntityService {

    /* ===================== Atributos ======================= */

    private final UsuarioClient usuarioClient; // Cliente para consumir el microservicio de usuarios
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class); // Logger
    private final Tracer tracer; // Propiedad para registrar eventos en el log de la aplicación para visualizarlos en la traza de la aplicación

    /* ===================== Métodos ===================== */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            Usuario usuario = usuarioClient.findByUsername(username); // Busca el usuario en el microservicio de usuarios

            Optional.ofNullable(usuario).orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " no existe")); // Mediante Optional, si el usuario no existe, lanza una excepción

            List<GrantedAuthority> authorities = usuario.getRoles().stream() // Convierte la lista de roles en una lista de GrantedAuthority
                    .map(role -> new SimpleGrantedAuthority(role.getName())) // Mapea cada rol a un GrantedAuthority
                    .peek(authority -> log.info("Role: " + authority.getAuthority())) // Muestra en consola el rol
                    .collect(Collectors.toList()); // Convierte la lista de GrantedAuthority en una lista

            //log.info("Usuario autenticado: " + username); // Muestra en consola el usuario autenticado

            return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities); // Retorna un UserDetails con los datos del usuario autenticado

        } catch (FeignException e) {
            log.error(String.format("El usuario %s no existe en el sistema",username)); // Mostramos el error al obtener el usuario
            tracer.currentSpan().tag("error.mensaje", "El usuario " + username + " no existe en el sistema"); // Registramos el error en la traza de la aplicación
            throw new UsernameNotFoundException(String.format("El usuario %s no existe en el sistema",username)); // Lanzamos la excepción
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioClient.findByUsername(username);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        return usuarioClient.update(id, usuario);
    }

    /* ===================== Constructores ===================== */

    @Autowired
    public UsuarioService(UsuarioClient usuarioClient, Tracer tracer) {
        this.usuarioClient = usuarioClient;
        this.tracer = tracer;
    }
}
