package com.nelcamacho.oauthservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /* =================== Atributos =================== */

    private final UserDetailsService userService;
    private final AuthenticationEventPublisher authenticationEventPublisher;

    /* =================== Métodos =================== */

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder())
                .and().authenticationEventPublisher(this.authenticationEventPublisher);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to("info","env")).authenticated()
                .requestMatchers(EndpointRequest.to("health")).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /* =================== Constructores =================== */

    @Autowired
    public SecurityConfiguration(UserDetailsService usuarioService, AuthenticationEventPublisher authenticationEventPublisher) {
        this.userService = usuarioService;
        this.authenticationEventPublisher = authenticationEventPublisher;
    }

    public SecurityConfiguration(boolean disableDefaults, UserDetailsService userService, AuthenticationEventPublisher authenticationEventPublisher) {
        super(disableDefaults);
        this.userService = userService;
        this.authenticationEventPublisher = authenticationEventPublisher;
    }
}
