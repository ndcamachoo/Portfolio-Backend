package com.nelcamacho.oauthservice.services;

import com.nelcamacho.oauthservice.client.UserClient;
import com.nelcamacho.oauthservice.models.User;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IEntityService {

    /* ===================== Atributos ======================= */

    private final UserClient userClient;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /* ===================== Métodos ===================== */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            User user = userClient.findByUsername(username);

            Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " doesn't exist"));

            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .peek(authority -> log.info("[ROLE] -> " + authority.getAuthority()))
                    .collect(Collectors.toList());

            log.info("[AUTHENTICATED USER] -> " + username);

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);

        } catch (FeignException e) {
            log.error(String.format("USER %s DOESN'T EXIST IN THE SYSTEM", username));
            throw new UsernameNotFoundException(String.format("USER %s DOESN'T EXIST IN THE SYSTEM",username));
        }
    }

    @Override
    public User findByUsername(String username) {
        return userClient.findByUsername(username);
    }

    @Override
    public User update(Long id, User user) {
        return userClient.update(id, user);
    }

    /* ===================== Constructores ===================== */

    @Autowired
    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }
}
