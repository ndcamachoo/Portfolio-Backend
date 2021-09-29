package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Domicilio;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.service.OdontologoServiceImpl;
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
public class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;
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
    @DisplayName("Insertar By API -> Odontologo")
    void saveOdontologo(){

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/save")
                    .content(asJsonString(Odefault))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(201))
                    .andExpect(content().contentType("application/json"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID By API -> Odontologo")
    void findOdontologoById() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 1))
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(Odefault.isAdmin()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.user").value(Odefault.getUsuario().getUser()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.password").value(Odefault.getUsuario().getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos By API -> Odontologo")
    void updateOdontologo() {

        Odontologo tmp = odontologoService.findById(1L).orElse(null);
        Assertions.assertNotNull(tmp);

        tmp.getUsuario().setUser("user");
        tmp.setAdmin(false);
        tmp.getDomicilio().setLocalidad("Córdoba");
        tmp.getDomicilio().setProvincia("Córdoba");

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/update")
                    .content(asJsonString(tmp))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Odontologo with id: " + tmp.getId() + " was updated"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(4)
    @DisplayName("Eliminar campos By API -> Odontologo")
    void deleteOdontologo() {

        Integer id = 1;

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/delete/{id}", id))
                    .andDo(print())
                    .andExpect(content().contentType("text/plain; charset=UTF-8"))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Odontologo with id: " + id + " was deleted"));
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
