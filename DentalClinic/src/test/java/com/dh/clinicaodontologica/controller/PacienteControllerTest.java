package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Domicilio;
import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.service.PacienteServiceImpl;
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

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
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
    @DisplayName("Insertar campo By API -> Paciente")
    void savePaciente(){

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/save")
                    .content(asJsonString(Pdefault))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(201))
                    .andExpect(content().contentType("application/json"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID By API-> Paciente")
    void findPacienteById() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1))
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value(Pdefault.getDNI()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.user").value(Pdefault.getUsuario().getUser()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.password").value(Pdefault.getUsuario().getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos By API -> Paciente")
    void updatePaciente() {

        Paciente tmp = pacienteService.findById(1L).orElse(null);
        Assertions.assertNotNull(tmp);

        tmp.getUsuario().setUser("user");
        tmp.setFechaIngreso(LocalDate.of(2021,9,28));
        tmp.getDomicilio().setLocalidad("Córdoba");
        tmp.getDomicilio().setProvincia("Córdoba");

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/pacientes/update")
                    .content(asJsonString(tmp))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Paciente with id: " + tmp.getId() + " was updated"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(4)
    @DisplayName("Eliminar campos By API -> Paciente")
    void deletePaciente() {

        Integer id = 1;

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/delete/{id}", id))
                    .andDo(print())
                    .andExpect(content().contentType("text/plain; charset=UTF-8"))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Paciente with id: " + id + " was deleted"));
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
