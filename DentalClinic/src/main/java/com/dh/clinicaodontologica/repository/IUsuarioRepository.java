package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.user = ?1 AND u.password = ?2")
    Usuario findUsuarioByLogin(String Username, String Password);

    @Query("SELECT u FROM Usuario u WHERE u.user = ?1")
    Usuario findUsuarioByUser(String username);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = ?1 AND u.apellido = ?2")
    Usuario findUsuarioByNombreAndApellido(String Nombre, String Apellido);
}
