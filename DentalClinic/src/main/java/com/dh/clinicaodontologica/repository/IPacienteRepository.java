package com.dh.clinicaodontologica.repository;


import com.dh.clinicaodontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {


    @Query("SELECT p FROM Paciente p WHERE p.DNI = ?1")
    Paciente findPacienteByDNI(Integer DNI);

    @Query("SELECT p FROM Paciente p WHERE p.usuario.nombre = ?1 AND p.usuario.apellido = ?2")
    Paciente findPacienteByFullname(String Name, String LastName);

}
