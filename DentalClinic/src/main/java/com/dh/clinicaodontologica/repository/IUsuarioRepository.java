package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.user = ?1 AND u.password = ?2")
    Usuario findUsuarioByLogin(String Username, String Password);

    //@Query("SELECT u FROM Usuario u WHERE u.user = ?1")
    @Query(value = "SELECT * FROM Usuarios WHERE user = :username LIMIT 1", nativeQuery = true)
    Usuario findUsuarioByUser(@Param("username") String username);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = ?1 AND u.apellido = ?2")
    Usuario findUsuarioByNombreAndApellido(String Nombre, String Apellido);
}
