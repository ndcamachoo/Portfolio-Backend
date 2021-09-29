package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.service.OdontologoServiceImpl;
import com.dh.clinicaodontologica.service.UsuarioServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    /* ================== Atributos ====================*/

    private final OdontologoServiceImpl odontologoService;
    private final UsuarioServiceImpl usuarioService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    /* ================== GET ====================*/

    @GetMapping("")
    public List<Odontologo> findAllOdontologos(){
        logger.debug("Lectura general de la entidad Odontologos");
        return odontologoService.findAll();
    }

    @GetMapping("/{id}")
    public Odontologo findOdontologoById(@PathVariable Long id){
        logger.debug("Búsqueda en la entidad Odontologos por el ID: " + id);
        return odontologoService.findById(id).get();
    }

    /* ================== POST ====================*/

    @PostMapping("/save")
    public ResponseEntity<?> saveOdontologo(@RequestBody Odontologo odontologo){

        ResponseEntity response;

        if(usuarioService.findUsuarioByUsername(odontologo.getUsuario().getUser()) != null){
            response = new ResponseEntity("The username already exists, please change it", HttpStatus.CONFLICT);
            logger.debug("Guardado en la entidad Odontologos fallida -> Usuario ya existente (CONFLICTO)");
        }else if(odontologoService.findOdontologoByNumeroMatricula(odontologo.getNumeroMatricula()) != null){
            response = new ResponseEntity("The registration number already exists, please change it", HttpStatus.CONFLICT);
            logger.debug("Guardado en la entidad Odontologos fallida -> Matricula ya existente (CONFLICTO)");
        }else{
            response = new ResponseEntity(odontologoService.save(odontologo), HttpStatus.CREATED);
            logger.debug("Dato guardado correctamente la entidad Odontologos ");
        }

        return response;
    }

    /* ================== PUT ====================*/

    @PutMapping("/update")
    public ResponseEntity<?> updateOdontologo(@RequestBody Odontologo odontologo){

        ResponseEntity response;

        if(odontologoService.findById(odontologo.getId()).isPresent()){
            response = new ResponseEntity(odontologoService.update(odontologo), HttpStatus.OK);
            logger.debug("Dato actualizado correctamente la entidad Odontologos ");
        }else{
            response = new ResponseEntity("Odontologo with id:" + odontologo.getId() + " don't exist", HttpStatus.NO_CONTENT);
            logger.debug("Actualización de campos en la entidad Odontologos fallida -> ID no existente (NO_CONTENT)");
        }

        return response;
    }

    /* ================== DELETE ====================*/

    @DeleteMapping("/delete/{id}")
    public String deleteOdontologo(@PathVariable Long id){
        return odontologoService.delete(id);
    }

    /* ================== Constructor ====================*/

    @Autowired
    public OdontologoController(OdontologoServiceImpl odontologoService, UsuarioServiceImpl usuarioService) {
        this.odontologoService = odontologoService;
        this.usuarioService = usuarioService;
    }

}
