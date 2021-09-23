package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.dto.TurnoDTO;
import com.dh.clinicaodontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITurnoRepository extends JpaRepository<Turno,Long> {

    @Query("SELECT t FROM Turno t WHERE t.paciente.usuario.user = ?1")
    List<Turno> findTurnosByPaciente(String UserPaciente);

    @Query("SELECT t FROM Turno t WHERE t.odontologo.usuario.user = ?1")
    List<Turno> findTurnosByOdontologo(String UserOdontologo);

}
