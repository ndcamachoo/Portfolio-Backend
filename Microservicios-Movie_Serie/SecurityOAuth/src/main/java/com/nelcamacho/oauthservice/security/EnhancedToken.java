package com.nelcamacho.oauthservice.security;

import com.nelcamacho.oauthservice.models.User;
import com.nelcamacho.oauthservice.services.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnhancedToken implements TokenEnhancer {

    /* ==================== Atributos ==================== */

    private final IEntityService userService;

    /* ==================== Métodos ==================== */

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> additionalInfo = new HashMap<>();

        User user = userService.findByUsername(oAuth2Authentication.getName());

        additionalInfo.put("name", user.getName());
        additionalInfo.put("lastname", user.getLastname());
        additionalInfo.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);

        return oAuth2AccessToken;

    }

    /* ==================== Constructores ==================== */

    @Autowired
    public EnhancedToken(IEntityService userService) {
        this.userService = userService;
    }
}
