package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.Domicilio;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoServiceImpl odontologoService;
    Domicilio Ddefault;
    Usuario Udefault;
    Odontologo Odefault;

    @BeforeEach
    void init(){
        Ddefault = new Domicilio("Falsa", "123", "Bogotá", "Cundinamarca");
        Udefault = new Usuario("Usuario", "Default", "userd", "1234");
        Odefault = new Odontologo(123456789,true, Udefault, Ddefault);
    }

    @Test
    @Order(1)
    @DisplayName("Insertar campo -> Odontologo")
    void saveOdontologo(){
        Odontologo Odb = odontologoService.save(Odefault);

        Assertions.assertNotNull(Odb);
        Assertions.assertNotNull(Odb.getId());
        Assertions.assertEquals(Odb.getUsuario(), Udefault);
        Assertions.assertEquals(Odb.getDomicilio(), Ddefault);
    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID -> Odontologo")
    void findOdontologoById() {

        Odontologo Odb = odontologoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Odb);
        Assertions.assertNotNull(Odb.getId());
        Assertions.assertEquals(Odb.getUsuario().getUser(), Odefault.getUsuario().getUser());
        Assertions.assertEquals(Odb.getUsuario().getPassword(), Odefault.getUsuario().getPassword());
        Assertions.assertEquals(Odb.getDomicilio().getCalle(), Odefault.getDomicilio().getCalle());
        Assertions.assertEquals(Odb.getDomicilio().getProvincia(), Odefault.getDomicilio().getProvincia());
        Assertions.assertTrue(Odb instanceof Odontologo);

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos -> Odontologo")
    void updateOdontologo() {

        Odontologo Odb = odontologoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Odb);

        Odb.getUsuario().setUser("user");
        Odb.setAdmin(false);
        Odb.getDomicilio().setLocalidad("Córdoba");
        Odb.getDomicilio().setProvincia("Córdoba");
        odontologoService.update(Odb);

        Odontologo OdbUpdate = odontologoService.findById(1L).orElse(null);
        Assertions.assertNotNull(OdbUpdate);

        Assertions.assertEquals(OdbUpdate.getId(),Odb.getId());
        Assertions.assertNotEquals(OdbUpdate.getUsuario().getUser(), Odefault.getUsuario().getUser());
        Assertions.assertNotEquals(OdbUpdate.isAdmin(), Odefault.isAdmin());
        Assertions.assertNotEquals(OdbUpdate.getDomicilio().getLocalidad(), Odefault.getDomicilio().getLocalidad());
        Assertions.assertNotEquals(OdbUpdate.getDomicilio().getProvincia(), Odefault.getDomicilio().getProvincia());

    }

    @Test
    @Order(4)
    @DisplayName("Eliminar campos -> Odontologo")
    void deleteOdontologo() {

        Odontologo Odb = odontologoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Odb);

        odontologoService.delete(Odb.getId());
        Odontologo Res = odontologoService.findById(1L).orElse(null);
        Assertions.assertNull(Res);

    }

}
