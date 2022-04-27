package com.nelcamacho.oauthservice.events;

import com.nelcamacho.oauthservice.models.User;
import com.nelcamacho.oauthservice.services.IEntityService;
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
    private final IEntityService userService;

    /* ================== Métodos ================== */

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {

        if(authentication.getDetails() instanceof WebAuthenticationDetails) {
            return;
        }

        UserDetails user = (UserDetails) authentication.getPrincipal();

        User user1 = userService.findByUsername(authentication.getName());

        if(user1.getIntentos() == null || user1.getIntentos() > 0) {
            user1.setIntentos(0);
            userService.update(user1.getId(), user1);
        }

    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

        log.error("[AUTHENTICATION ERROR] -> " + exception.getMessage());

        try {

            StringBuilder errors = new StringBuilder();
            errors.append("[AUTHENTICATION ERROR] -> " + exception.getMessage());
            User user = userService.findByUsername(authentication.getName());

            if(user.getIntentos() == null) {
                user.setIntentos(0);
            }

            user.setIntentos(user.getIntentos() + 1);
            log.info("[AUTHENTICATION ATTEMPTS] ->" + user.getIntentos());
            errors.append("\n[AUTHENTICATION ATTEMPTS] -> " + user.getIntentos());

            if(user.getIntentos() >= 3) {
                user.setEnabled(false);
                log.error("USER DISABLED FOR ATTEMPTS: " + user.getUsername());
                errors.append("\nUSER DISABLED FOR ATTEMPTS: " + user.getUsername());
            }

            userService.update(user.getId(), user);

        } catch (FeignException e) {
            log.error(String.format("USER %s DOESN'T EXIST IN THE SYSTEM", authentication.getName()));
        }

    }

    /* ================== Constructores ================== */

    @Autowired
    public AuthenticationSuccessErrorHandler(IEntityService userService) {
        this.userService = userService;
    }
}
