package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.Usuario;

import java.util.List;

public interface IUsuarioService{

    /* ============== Métodos ================= */
    List<Usuario> findAllUsuarios();
    Usuario findUsuarioByUsername(String Username);
    Usuario findUsuarioByLogin(String Username, String Password);

}
