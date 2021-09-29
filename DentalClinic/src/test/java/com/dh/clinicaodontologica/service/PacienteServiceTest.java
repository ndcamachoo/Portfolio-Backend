package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.Domicilio;
import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteServiceImpl pacienteService;
    Domicilio Ddefault;
    Usuario Udefault;
    Paciente Pdefault;

    @BeforeEach
    void init(){
        Ddefault = new Domicilio("Falsa", "123", "Bogotá", "Cundinamarca");
        Udefault = new Usuario("Usuario", "Default", "userd", "1234");
        Pdefault = new Paciente(123456789, LocalDate.of(2021,9,27), Udefault, Ddefault);
    }

    @Test
    @Order(1)
    @DisplayName("Insertar campo -> Paciente")
    void savePaciente(){
        Paciente Pdb = pacienteService.save(Pdefault);

        Assertions.assertNotNull(Pdb);
        Assertions.assertNotNull(Pdb.getId());
        Assertions.assertEquals(Pdb.getUsuario(), Udefault);
        Assertions.assertEquals(Pdb.getDomicilio(), Ddefault);
    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID -> Paciente")
    void findPacienteById() {

        Paciente Pdb = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(Pdb);
        Assertions.assertNotNull(Pdb.getId());
        Assertions.assertEquals(Pdb.getUsuario().getUser(), Pdefault.getUsuario().getUser());
        Assertions.assertEquals(Pdb.getUsuario().getPassword(), Pdefault.getUsuario().getPassword());
        Assertions.assertEquals(Pdb.getDomicilio().getCalle(), Pdefault.getDomicilio().getCalle());
        Assertions.assertEquals(Pdb.getDomicilio().getProvincia(), Pdefault.getDomicilio().getProvincia());
        Assertions.assertTrue(Pdb instanceof Paciente);

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos -> Paciente")
    void updatePaciente() {

        Paciente Pdb = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(Pdb);

        Pdb.getUsuario().setUser("user");
        Pdb.setFechaIngreso(LocalDate.of(2021,9,28));
        Pdb.getDomicilio().setLocalidad("Córdoba");
        Pdb.getDomicilio().setProvincia("Córdoba");
        pacienteService.update(Pdb);

        Paciente PdbUpdate = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(PdbUpdate);

        Assertions.assertEquals(PdbUpdate.getId(),Pdb.getId());
        Assertions.assertNotEquals(PdbUpdate.getUsuario().getUser(), Pdefault.getUsuario().getUser());
        Assertions.assertNotEquals(PdbUpdate.getFechaIngreso(), Pdefault.getFechaIngreso());
        Assertions.assertNotEquals(PdbUpdate.getDomicilio().getLocalidad(), Pdefault.getDomicilio().getLocalidad());
        Assertions.assertNotEquals(PdbUpdate.getDomicilio().getProvincia(), Pdefault.getDomicilio().getProvincia());

    }

    @Test
    @Order(4)
    @DisplayName("Eliminar campos -> Paciente")
    void deletePaciente() {

        Paciente Odb = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(Odb);

        pacienteService.delete(Odb.getId());
        Paciente Res = pacienteService.findById(1L).orElse(null);
        Assertions.assertNull(Res);

    }

}
