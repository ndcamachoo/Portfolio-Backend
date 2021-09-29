package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    @Autowired
    private OdontologoServiceImpl odontologoService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private TurnoServiceImpl turnoService;

    Domicilio Ddefault;
    Domicilio Ddefault2;
    Usuario Udefault;
    Usuario Udefault2;
    Paciente Pdefault;
    Odontologo Odefault;
    Turno Tdefault;

    @BeforeEach
    void init(){
        Ddefault = new Domicilio("Falsa", "123", "Bogotá", "Cundinamarca");
        Ddefault2 = new Domicilio("Falsa", "123", "Bogotá", "Cundinamarca");

        Udefault = new Usuario("Usuario", "Default", "userd", "1234");
        Udefault2 = new Usuario("Usuario2", "Default", "userd2", "1234");
        Odefault = new Odontologo(123456789,true, Udefault, Ddefault);

        Pdefault = new Paciente(123456789, LocalDate.of(2021,9,27), Udefault2, Ddefault2);
        Tdefault = new Turno(Pdefault,Odefault, LocalDate.of(2021,9,27), LocalTime.of(10,0));
    }

    @Test
    @Order(1)
    @DisplayName("Insertar campo -> Turno")
    void saveTurno(){

        Odontologo Odb = odontologoService.save(Odefault);
        Paciente Pdb = pacienteService.save(Pdefault);
        Turno tmp = new Turno(Pdb, Odb, LocalDate.of(2021,9,27), LocalTime.of(10,0));
        Turno Tdb = turnoService.save(tmp);

        Assertions.assertNotNull(Tdb);
        Assertions.assertNotNull(Tdb.getId());
        Assertions.assertEquals(Tdb.getPaciente().getUsuario(), Udefault2);
        Assertions.assertEquals(Tdb.getOdontologo().getUsuario(), Udefault);

        Assertions.assertEquals(Tdb.getPaciente().getDomicilio(), Ddefault2);
        Assertions.assertEquals(Tdb.getOdontologo().getDomicilio(), Ddefault);

        Assertions.assertEquals(Tdb.getOdontologo().getNumeroMatricula(),Odefault.getNumeroMatricula());
        Assertions.assertEquals(Tdb.getPaciente().getDNI(),Pdefault.getDNI());
    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID -> Turno")
    void findTurnoById() {

        Turno Tdb = turnoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Tdb);
        Assertions.assertNotNull(Tdb.getId());

        Assertions.assertEquals(Tdb.getPaciente().getUsuario().getUser(), Pdefault.getUsuario().getUser());
        Assertions.assertEquals(Tdb.getPaciente().getUsuario().getPassword(), Pdefault.getUsuario().getPassword());
        Assertions.assertEquals(Tdb.getPaciente().getDomicilio().getCalle(), Pdefault.getDomicilio().getCalle());
        Assertions.assertEquals(Tdb.getPaciente().getDomicilio().getProvincia(), Pdefault.getDomicilio().getProvincia());

        Assertions.assertEquals(Tdb.getOdontologo().getUsuario().getUser(), Odefault.getUsuario().getUser());
        Assertions.assertEquals(Tdb.getOdontologo().getUsuario().getPassword(), Odefault.getUsuario().getPassword());
        Assertions.assertEquals(Tdb.getOdontologo().getDomicilio().getCalle(), Odefault.getDomicilio().getCalle());
        Assertions.assertEquals(Tdb.getOdontologo().getDomicilio().getProvincia(), Odefault.getDomicilio().getProvincia());

        Assertions.assertEquals(Tdb.getFechaTurno(), Tdefault.getFechaTurno());
        Assertions.assertEquals(Tdb.getHoraTurno(), Tdefault.getHoraTurno());

        Assertions.assertTrue(Tdb instanceof Turno);

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos -> Turnos")
    void updateTurno() {

        Turno Tdb = turnoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Tdb);

        Tdb.setFechaTurno(LocalDate.of(2021,9,28));
        Tdb.setHoraTurno(LocalTime.of(11,0));

        turnoService.update(Tdb);

        Turno TdbUpdate = turnoService.findById(1L).orElse(null);
        Assertions.assertNotNull(TdbUpdate);

        Assertions.assertEquals(TdbUpdate.getId(),Tdb.getId());
        Assertions.assertEquals(TdbUpdate.getPaciente().getUsuario().getUser(), Tdefault.getPaciente().getUsuario().getUser());
        Assertions.assertEquals(TdbUpdate.getOdontologo().getUsuario().getUser(), Odefault.getUsuario().getUser());

        Assertions.assertNotEquals(TdbUpdate.getFechaTurno(), Tdefault.getFechaTurno());
        Assertions.assertNotEquals(TdbUpdate.getHoraTurno(), Tdefault.getHoraTurno());

    }

    @Test
    @Order(4)
    @DisplayName("Eliminar campos -> Turnos")
    void deleteTurno() {

        Turno Tdb = turnoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Tdb);

        turnoService.delete(Tdb.getId());
        Turno Res = turnoService.findById(1L).orElse(null);
        Assertions.assertNull(Res);

    }

}
