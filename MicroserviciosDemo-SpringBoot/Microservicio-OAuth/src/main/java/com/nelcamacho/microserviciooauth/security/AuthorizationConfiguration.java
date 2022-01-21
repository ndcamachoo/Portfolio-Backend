package com.nelcamacho.microserviciooauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    /* ================== Atributos ================== */

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EnhancedToken enhancedToken;
    private final Environment env;

    /* ================== Métodos ================== */


    /* === Security == */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {

        security.tokenKeyAccess("permitAll()") // Se configura el acceso al endpoint de solicitud del token *POST: /oauth/token* (Con el método permitAll() se permite el acceso a cualquier usuario)
                .checkTokenAccess("isAuthenticated()"); // Se encarga de validar el token *GET: /oauth/check_token* (Con el método isAuthenticated() se valida que el usuario esté autenticado)
                                                       // La validación del token se realiza mediante el Header utilizando Authorization Basic: ClientId:ClientSecret

    }

    /* === Clients === */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory() // Se configura el almacenaje del cliente en memoria (Se puede configurar en una base de datos)
                .withClient(env.getProperty("config.security.oauth.client.id")) // Se configura el nombre del cliente (Representa el identificador del cliente)
                .secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret"))) // Se configura la clave del cliente (Representa la contraseña para el uso del cliente)
                .scopes("read", "write") // Se configura los permisos del cliente (read: leer, write: escribir, modificar)
                .authorizedGrantTypes("password", "refresh_token") // Se configura los tipos de autorización del cliente (password: usuario y contraseña, refresh_token: refrescar el token)
                .accessTokenValiditySeconds(3600) // Se configura el tiempo de validez del token (3600 segundos = 1 hora)
                .refreshTokenValiditySeconds(3600); // Se configura el tiempo de validez del token de refresco (3600 segundos = 1 hora)
                //.and()
                //.withClient("backend-app") // Se puede configurar más clientes
    }

    /* === Endpoints === */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain(); // Se configura el mecanismo de mejora del token

        tokenEnhancerChain.setTokenEnhancers( // Se configura la lista de mejoras del token
                Arrays.asList( // Se configura la lista de mejoras del token
                        enhancedToken,
                        accessTokenConverter()
                )
        );

        // Se configura el AuthenticationManager para que el AuthorizationServer pueda autenticar a los usuarios
        endpoints.authenticationManager(authenticationManager)

                // Almacena los tokens convertidos en formato JWT
                .tokenStore(tokenStore())

                // Conversor de datos al formato JWT
                .accessTokenConverter(accessTokenConverter())

                // Mejora del token
                .tokenEnhancer(tokenEnhancerChain);

    }


    /* === Beans === */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){

        // Se crea un nuevo conversor de datos al formato JWT

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(Base64.getEncoder().encodeToString(Objects.requireNonNull(env.getProperty("config.security.oauth.jwt.key")).getBytes())); // Se configura la clave de firma (Codigo secreto)
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore(){

        // Permite crear el token con toda la información del usuario y almacenarlo.
        return new JwtTokenStore(accessTokenConverter());
    }

    /* ================== Constructor ================== */

    @Autowired
    public AuthorizationConfiguration(BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, EnhancedToken enhancedToken, Environment env) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.enhancedToken = enhancedToken;
        this.env = env;
    }
}
