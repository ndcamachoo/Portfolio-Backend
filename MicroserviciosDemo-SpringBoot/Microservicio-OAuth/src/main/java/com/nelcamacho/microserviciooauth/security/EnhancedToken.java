package com.nelcamacho.microserviciooauth.security;

import com.nelcamacho.microserviciooauth.services.IEntityService;
import com.nelcamacho.microserviciousuarioscommons.models.Usuario;
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

    private final IEntityService usuarioService;

    /* ==================== Métodos ==================== */

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        // Permite agregar información al token con pares clave-valor
        Map<String, Object> additionalInfo = new HashMap<>();

        // Obtener datos relevantes del usuario
        Usuario usuario = usuarioService.findByUsername(oAuth2Authentication.getName());

        // Agregar información al token
        additionalInfo.put("nombre", usuario.getNombre());
        additionalInfo.put("apellido", usuario.getApellido());
        additionalInfo.put("email", usuario.getEmail());

        // Implementar la información al token
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);

        return oAuth2AccessToken;

    }

    /* ==================== Constructores ==================== */

    @Autowired
    public EnhancedToken(IEntityService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
