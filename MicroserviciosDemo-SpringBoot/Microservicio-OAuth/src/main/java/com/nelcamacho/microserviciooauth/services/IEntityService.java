package com.nelcamacho.microserviciooauth.services;

import com.nelcamacho.microserviciousuarioscommons.models.Usuario;

public interface IEntityService {

    /* ================== Métodos ================== */
    Usuario findByUsername(String username);
    Usuario update(Long id, Usuario usuario);

}
