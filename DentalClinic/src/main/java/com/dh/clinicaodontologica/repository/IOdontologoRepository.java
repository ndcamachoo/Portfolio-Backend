package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.user = ?1")
    Usuario findUsuarioByUsername(String userName);

    @Query("SELECT o FROM Odontologo o WHERE o.numeroMatricula = ?1")
    Odontologo findOdontologoByNumeroMatricula(Integer numeroMatricula);

    @Query("SELECT o FROM Odontologo o INNER JOIN Usuario u ON o.usuario.id = u.id WHERE u.user = ?1")
    Odontologo findOdontologoByUsername(String Username);

    @Query("SELECT o FROM Odontologo o WHERE o.usuario.nombre = ?1 AND o.usuario.apellido = ?2")
    Odontologo findOdontologoByFullname(String Name, String LastName);

    @Query("SELECT u.id, u.user FROM Usuario u ORDER BY u.user ASC")
    List<Object> listUsernames();



}
