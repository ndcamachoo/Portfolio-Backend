package com.nelcamacho.microserviciooauth.client;

import com.nelcamacho.microserviciousuarioscommons.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioClient {

    /* =============== Métodos =================== */

    @GetMapping("/usuarios/search/username")
    Usuario findByUsername(@RequestParam String username);

    @PutMapping("/usuarios/{id}")
    Usuario update(@RequestParam Long id, @RequestBody Usuario usuario);

}
