package com.dh.clinicaodontologica.repository;


import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Modifying
    @Query("UPDATE Paciente p SET p.DNI = ?1 WHERE p.id = ?2")
    void updateDNI(Integer newDNI, Integer id);

    @Query("SELECT p FROM Paciente p WHERE p.DNI = ?1")
    Paciente findPacienteByDNI(Integer DNI);

    @Query("SELECT u FROM Usuario u WHERE u.user = ?1")
    Usuario findUsuarioByUsername(String username);

    @Query("SELECT p FROM Paciente p WHERE p.usuario.nombre = ?1 AND p.usuario.apellido = ?2")
    Paciente findPacienteByFullname(String Name, String LastName);

}
