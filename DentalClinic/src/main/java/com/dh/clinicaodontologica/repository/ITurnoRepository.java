package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ITurnoRepository extends JpaRepository<Turno,Long> {

    @Query("SELECT t FROM Turno t WHERE t.paciente.usuario.user = ?1")
    List<Turno> findTurnosByPaciente(String UserPaciente);

    @Query("SELECT t FROM Turno t WHERE t.odontologo.usuario.user = ?1")
    List<Turno> findTurnosByOdontologo(String UserOdontologo);

    @Query("SELECT t FROM Turno t WHERE t.fechaTurno BETWEEN ?1 AND ?2")
    List<Turno> listaTurnosSemanales(String Fecha1, String Fecha2);


}
