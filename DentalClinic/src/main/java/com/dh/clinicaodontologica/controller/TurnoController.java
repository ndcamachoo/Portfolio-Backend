package com.dh.clinicaodontologica.controller;


import com.dh.clinicaodontologica.dto.TurnoDTO;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.model.Turno;
import com.dh.clinicaodontologica.service.OdontologoServiceImpl;
import com.dh.clinicaodontologica.service.PacienteServiceImpl;
import com.dh.clinicaodontologica.service.TurnoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    /* ================== Atributos ==================== */

    private final TurnoServiceImpl turnoService;
    private static final Logger logger = Logger.getLogger(TurnoController.class);
    /* Temporal -> Solo por test*/

    private final PacienteServiceImpl pacienteService;
    private final OdontologoServiceImpl odontologoService;

    /* ================== GET ==================== */

    @GetMapping("")
    public List<TurnoDTO> findAllTurnos(){
        logger.debug("Lectura general de la entidad Turnos");
        return turnoService.findAll().stream().map(turnoService::mapToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Turno findTurnosById(@PathVariable Long id){
        logger.debug("Búsqueda en la entidad Turnos por el ID: " + id);
        return turnoService.findById(id).get();
    }

    @GetMapping("/searchp/{username}")
    public List<TurnoDTO> findTurnosByPaciente(@PathVariable String username){
        logger.debug("Lectura general de la entidad Turnos filtrada por usuario del Paciente");
        return turnoService.findTurnosByPaciente(username).stream().map(turnoService::mapToDTO).collect(Collectors.toList());
    }

    @GetMapping("/searcho/{username}")
    public List<TurnoDTO> findTurnosByOdontologo(@PathVariable String username){
        logger.debug("Lectura general de la entidad Turnos filtrada por usuario del Odontologo");
        return turnoService.findTurnosByOdontologo(username).stream().map(turnoService::mapToDTO).collect(Collectors.toList());
    }

    /* ================== POST ====================*/

    @PostMapping("/save")
    public ResponseEntity saveTurno(@RequestBody TurnoDTO turnoDTO){

        ResponseEntity response = null;
        String[] nombreOdontologo = turnoDTO.getNombreOdontologo().split(", ");
        String[] nombrePaciente = turnoDTO.getNombrePaciente().split(", ");

        if(odontologoService.findOdontologoByFullname(nombreOdontologo[1],nombreOdontologo[0]) == null){
            response = new ResponseEntity("The dentist you are entering does not exist in the database, please change it.", HttpStatus.NOT_FOUND);
            logger.debug("Guardado en la entidad Turnos fallida -> Odontologo inexistente (NOT_FOUND)");
        }else if(pacienteService.findPacienteByFullname(nombrePaciente[1],nombrePaciente[0]) == null){
            response = new ResponseEntity("The patient you are entering does not exist in the database, please change it.", HttpStatus.NOT_FOUND);
            logger.debug("Guardado en la entidad Turnos fallida -> Paciente inexistente (NOT_FOUND)");
        }else{
            Turno turnoSave = turnoService.mapToEntity(turnoDTO);
            response = new ResponseEntity(turnoService.mapToDTO(turnoService.save(turnoSave)), HttpStatus.CREATED);
            logger.debug("Dato guardado correctamente la entidad Turnos");
        }

        return response;
    }

    @PostMapping("/test")
    public Object testTurno(){
        Odontologo odTmp = odontologoService.findById(1L).get();
        Paciente pcTmp = pacienteService.findById(1L).get();
        return turnoService.save(new Turno(pcTmp,odTmp, LocalDate.of(2021,9,19), LocalTime.of(12,47)));
    }


    /* ================== PUT ==================== */

    @PutMapping("/update")
    public ResponseEntity updateTurno(@RequestBody TurnoDTO turnoDTO){

        ResponseEntity response;
        Turno turnoUpdate = turnoService.mapToEntity(turnoDTO);
        System.out.println(turnoUpdate);

        String[] nombreOdontologo = turnoDTO.getNombreOdontologo().split(", ");
        String[] nombrePaciente = turnoDTO.getNombrePaciente().split(", ");

        if(odontologoService.findOdontologoByFullname(nombreOdontologo[1],nombreOdontologo[0]) == null){
            response = new ResponseEntity("The dentist you are entering does not exist in the database, please change it.", HttpStatus.NOT_FOUND);
            logger.debug("Actualización de campos en la entidad Turnos fallida -> Odontologo inexistente (NOT_FOUND)");
        }else if(pacienteService.findPacienteByFullname(nombrePaciente[1],nombrePaciente[0]) == null){
            response = new ResponseEntity("The patient you are entering does not exist in the database, please change it.", HttpStatus.NOT_FOUND);
            logger.debug("Actualización de campos en la entidad Turnos fallida -> Paciente inexistente (NOT_FOUND)");
        }else {
            if(turnoService.findById(turnoUpdate.getId()).isPresent()){
                logger.debug("Dato actualizado correctamente la entidad Turnos ");
                response = new ResponseEntity(turnoService.update(turnoUpdate), HttpStatus.OK);
            }else{
                logger.debug("Actualización de campos en la entidad Turnos fallida -> ID no existente (NO_CONTENT)");
                response = new ResponseEntity("Turno with id:" + turnoUpdate.getId() + " don't exist", HttpStatus.NO_CONTENT);
            }
        }


        return response;
    }


    /* ================== DELETE ==================== */

    @DeleteMapping("/delete/{id}")
    public String deleteTurno(@PathVariable Long id){
        return turnoService.delete(id);
    }

    /* ================== Constructor ==================== */

    /*@Autowired
    public TurnoController(TurnoServiceImpl turnoService) {
        this.turnoService = turnoService;
    }*/

    @Autowired
    public TurnoController(TurnoServiceImpl turnoService, PacienteServiceImpl pacienteService, OdontologoServiceImpl odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }
}
