package com.nelcamacho.oauthservice.security;

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

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");

    }

    /* === Clients === */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient(env.getProperty("config.security.oauth.client.id"))
                .secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);
    }

    /* === Endpoints === */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(
                        enhancedToken,
                        accessTokenConverter()
                )
        );

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain);

    }


    /* === Beans === */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(Base64.getEncoder().encodeToString(Objects.requireNonNull(env.getProperty("config.security.oauth.jwt.key")).getBytes()));
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore(){
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
