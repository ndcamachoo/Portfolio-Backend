package com.nelcamacho.oauthservice.services;


import com.nelcamacho.oauthservice.models.User;

public interface IEntityService {

    /* ================== Métodos ================== */
    User findByUsername(String username);
    User update(Long id, User user);

}
