package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.*;
import com.dh.clinicaodontologica.service.OdontologoServiceImpl;
import com.dh.clinicaodontologica.service.PacienteServiceImpl;
import com.dh.clinicaodontologica.service.TurnoServiceImpl;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnosControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OdontologoServiceImpl odontologoService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private TurnoServiceImpl turnoService;

    Domicilio Ddefault,Ddefault2;
    Usuario Udefault, Udefault2;
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
    @DisplayName("Insertar campo By API -> Turno")
    void saveTurno(){

        Odontologo Odb = odontologoService.save(Odefault);
        Paciente Pdb = pacienteService.save(Pdefault);
        Turno tmp = new Turno(Pdb, Odb, LocalDate.of(2021,9,27), LocalTime.of(10,0));

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/turnos/save")
                    .content(asJsonString(turnoService.mapToDTO(tmp)))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(201))
                    .andExpect(content().contentType("application/json"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    @DisplayName("Búsqueda por ID By API -> Turno")
    void findTurnoById() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/turnos/{id}", 1))
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nombrePaciente").value(Pdefault.getUsuario().getApellido() + ", " + Pdefault.getUsuario().getNombre()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nombreOdontologo").value(Odefault.getUsuario().getApellido() + ", " + Odefault.getUsuario().getNombre()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fechaTurno").value(Tdefault.getFechaTurno().toString()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.horaTurno").value(Tdefault.getHoraTurno().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    @DisplayName("Actualizar campos By API -> Turno")
    void updateTurno() {

        Turno Tdb = turnoService.findById(1L).orElse(null);
        Assertions.assertNotNull(Tdb);

        Tdb.setFechaTurno(LocalDate.of(2021,9,28));
        Tdb.setHoraTurno(LocalTime.of(11,0));

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/turnos/update")
                    .content(asJsonString(turnoService.mapToDTO(Tdb)))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Turno with id: " + Tdb.getId() + " was updated"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(4)
    @DisplayName("Eliminar campos By API -> Turno")
    void deleteTurno() {

        Integer id = 1;

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/delete/{id}", id))
                    .andDo(print())
                    .andExpect(content().contentType("text/plain; charset=UTF-8"))
                    .andExpect(status().is(200))
                    .andExpect(content().string("Turno with id: " + id + " was deleted"));
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
