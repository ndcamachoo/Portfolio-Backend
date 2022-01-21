package com.nelcamacho.microserviciooauth.events;

import brave.Tracer;
import com.nelcamacho.microserviciooauth.services.IEntityService;
import com.nelcamacho.microserviciousuarioscommons.models.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    /* ================== Atributos ================== */

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
    private final IEntityService usuarioService;
    private final Tracer tracer; // Habilitar el trace para Zipkin

    /* ================== Métodos ================== */

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) { // Evento de autenticación exitosa

        if(authentication.getDetails() instanceof WebAuthenticationDetails) { // Si el detalle de autenticación es una instancia de WebAuthenticationDetails
            return; // Retornamos
        }

        UserDetails user = (UserDetails) authentication.getPrincipal(); // Obtenemos el usuario autenticado
        log.info("Usuario autenticado: " + user.getUsername()); // Mostramos el usuario autenticado

        Usuario usuario = usuarioService.findByUsername(authentication.getName()); // Obtenemos el usuario que se autenticó

        if(usuario.getIntentos() == null || usuario.getIntentos() > 0) { // Si los intentos de autenticación no son nulos o son mayores a 0
            usuario.setIntentos(0); // Se establece el valor de intentos a 0
            usuarioService.update(usuario.getId(), usuario); // Actualizamos el usuario
        }

    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) { // Evento de autenticación fallida

        log.error("Error de autenticación: " + exception.getMessage()); // Mostramos el error de autenticación

        try {

            StringBuilder errors = new StringBuilder(); // Creamos un StringBuilder para almacenar los errores
            errors.append("Error de autenticación: " + exception.getMessage()); // Añadimos el error de autenticación
            Usuario usuario = usuarioService.findByUsername(authentication.getName()); // Obtenemos el usuario que intentó autenticarse

            if(usuario.getIntentos() == null) { // Si los intentos de autenticación no son nulos
                usuario.setIntentos(0); // Se establece el valor de intentos a 0
            }

            usuario.setIntentos(usuario.getIntentos() + 1); // Se incrementa el valor de intentos
            log.info("Intentos de autenticación: " + usuario.getIntentos()); // Mostramos los intentos de autenticación
            errors.append("\nIntentos de autenticación: " + usuario.getIntentos()); // Añadimos los intentos de autenticación

            if(usuario.getIntentos() >= 3) { // Si los intentos de autenticación son mayores o iguales a 3
                usuario.setEnabled(false); // Se deshabilita el usuario
                log.error("Usuario deshabilitado por intentos: " + usuario.getUsername()); // Mostramos el usuario deshabilitado
                errors.append("\nUsuario deshabilitado por intentos: " + usuario.getUsername()); // Añadimos el usuario deshabilitado
            }

            usuarioService.update(usuario.getId(), usuario); // Actualizamos el usuario

            tracer.currentSpan().tag("error.mensaje", errors.toString()); // Añadimos el error a la traza de la transacción

        } catch (FeignException e) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName())); // Mostramos el error al obtener el usuario
        }

    }

    /* ================== Constructores ================== */

    @Autowired
    public AuthenticationSuccessErrorHandler(IEntityService usuarioService, Tracer tracer) {
        this.usuarioService = usuarioService;
        this.tracer = tracer;
    }
}
