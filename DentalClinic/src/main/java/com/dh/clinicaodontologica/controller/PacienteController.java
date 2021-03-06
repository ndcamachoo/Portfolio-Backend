package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.service.PacienteServiceImpl;
import com.dh.clinicaodontologica.service.UsuarioServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    /* ================== Atributos ==================== */

    private final PacienteServiceImpl pacienteService;
    private final UsuarioServiceImpl usuarioService;
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    /* ================== GET ==================== */

    @GetMapping("")
    public List<Paciente> findAllPacientes(){
        logger.debug("Lectura general de la entidad Pacientes");
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public Paciente findPacienteById(@PathVariable Long id){
        logger.debug("Búsqueda en la entidad Pacientes por el ID: " + id);
        return pacienteService.findById(id).get();
    }

    /* ================== POST ====================*/

    @PostMapping("/save")
    public ResponseEntity<?> savePaciente(@RequestBody Paciente paciente){

        ResponseEntity response;

        if(usuarioService.findUsuarioByUsername(paciente.getUsuario().getUser()) != null){
            response = new ResponseEntity("The username already exists, please change it", HttpStatus.CONFLICT);
            logger.debug("Guardado en la entidad Pacientes fallida -> Usuario ya existente (CONFLICTO)");
        }else if(pacienteService.findPacienteByDNI(paciente.getDNI()) != null){
            response = new ResponseEntity("The DNI already exists, please change it", HttpStatus.CONFLICT);
            logger.debug("Guardado en la entidad Pacientes fallida -> DNI ya existente (CONFLICTO)");
        }else{
            response = new ResponseEntity(pacienteService.save(paciente), HttpStatus.CREATED);
            logger.debug("Dato guardado correctamente la entidad Pacientes");
        }

        return response;
    }

    /* ================== PUT ==================== */

    @PutMapping("/update")
    public ResponseEntity<?> updatePaciente(@RequestBody Paciente paciente){

        ResponseEntity response;

        if(pacienteService.findById(paciente.getId()).isPresent()){
            response = new ResponseEntity(pacienteService.update(paciente), HttpStatus.OK);
            logger.debug("Dato actualizado correctamente la entidad Pacientes ");
        }else{
            response = new ResponseEntity("Paciente with id:" + paciente.getId() + " don't exist", HttpStatus.NO_CONTENT);
            logger.debug("Actualización en la entidad Pacientes fallida -> ID no existente (NO_CONTENT)");
        }

        return response;
    }


    /* ================== DELETE ==================== */

    @DeleteMapping("/delete/{id}")
    public String deletePaciente(@PathVariable Long id){
        return pacienteService.delete(id);
    }

    /* ================== Constructor ==================== */

    @Autowired
    public PacienteController(PacienteServiceImpl pacienteService, UsuarioServiceImpl usuarioService) {
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
    }
}
