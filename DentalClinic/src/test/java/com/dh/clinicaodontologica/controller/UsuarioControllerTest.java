package com.dh.clinicaodontologica.controller;


import com.dh.clinicaodontologica.dto.UsuarioDTO;
import com.dh.clinicaodontologica.model.Domicilio;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Turno;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.service.OdontologoServiceImpl;
import com.dh.clinicaodontologica.service.UsuarioServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private OdontologoServiceImpl odontologoService;

    Domicilio Ddefault;
    Usuario Udefault;
    Odontologo Odefault;

    @BeforeEach
    void init(){
        Ddefault = new Domicilio("Falsa", "123", "Bogotá", "Cundinamarca");
        Udefault = new Usuario("Usuario", "Default", "user", "1234");
        Odefault = new Odontologo(123456789,true, Udefault, Ddefault);
    }

    @Test
    @Order(1)
    @DisplayName("Búsqueda por Username By API -> Usuarios")
    void findUsuarioByUsername() {

        odontologoService.save(Odefault);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/{username}", "user"))
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fullname").value(Udefault.getApellido() + ", " + Udefault.getNombre()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.user").value(Udefault.getUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID By API -> Usuarios")
    void findUsuariosById() {

        odontologoService.save(Odefault);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/search/{id}", 1))
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fullname").value(Udefault.getApellido() + ", " + Udefault.getNombre()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.user").value(Udefault.getUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos By API -> Usuarios")
    void updateUser() {

        Usuario Udb = usuarioService.findUsuarioById(1L).orElse(null);
        Assertions.assertNotNull(Udb);

        Udb.setNombre("User");
        Udb.setApellido("Default");

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/user/update")
                    .content(asJsonString(usuarioService.mapToDTO(Udb)))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().string("User with id: " + Udb.getId() + " was updated"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* ======================= Métodos =========================*/

    public static String asJsonString(Object object){
        try{
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new ParameterNamesModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

}
