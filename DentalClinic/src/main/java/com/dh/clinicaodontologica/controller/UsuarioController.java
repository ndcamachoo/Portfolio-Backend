package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.dto.UsuarioDTO;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.service.UsuarioServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsuarioController {

    /* ==================== Atributos =================== */

    private final UsuarioServiceImpl usuarioService;
    private static final Logger logger = Logger.getLogger(UsuarioController.class);

    /* ==================== GET =================== */

    @GetMapping("")
    public List<UsuarioDTO> findAll(){
        logger.debug("Lectura general de la entidad Usuarios");
        return usuarioService.findAllUsuarios().stream().map(usuarioService::mapToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findUsuarioByUser(@PathVariable String username){

        ResponseEntity response = null;
        Usuario usr = usuarioService.findUsuarioByUsername(username);

        if(usr == null){
            response = new ResponseEntity("The username is not found in the database, please change it.", HttpStatus.NOT_FOUND);
            logger.debug("Búsqueda de campos en la entidad Usuarios fallida -> Usuario inexistente existente (NOT_FOUND)");
        }else{
            logger.debug("Búsqueda en la entidad Usuarios filtrada por el usuario: " + username);
            response = new ResponseEntity(usuarioService.mapToDTO(usr), HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/search/{id}")
    public UsuarioDTO findUsuariosById(@PathVariable Long id){
        logger.debug("Búsqueda en la entidad Usuarios por el ID: " + id);
        return usuarioService.mapToDTO(usuarioService.findUsuarioById(id).get());
    }

    /* ================== PUT ==================== */

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UsuarioDTO usuarioDTO){

        ResponseEntity response;

        Usuario usuario = usuarioService.mapToEntity(usuarioDTO);

        if(usuarioService.findUsuarioByUsername(usuarioDTO.getUser()) == null){
            response = new ResponseEntity("The user you are entering does not exist in the database, please change it.", HttpStatus.NOT_FOUND);
            logger.debug("Actualización de campos en la entidad Usuarios fallida -> Usuario inexistente (NOT_FOUND)");
        }else {
            if(usuarioService.findUsuarioById(usuario.getId()).isPresent()){
                logger.debug("Dato actualizado correctamente la entidad Usuarios");
                response = new ResponseEntity(usuarioService.update(usuario), HttpStatus.OK);
            }else{
                response = new ResponseEntity("User with id:" + usuario.getId() + " don't exist", HttpStatus.NO_CONTENT);
                logger.debug("Actualización de campos en la entidad Usuarios fallida -> ID no existente (NO_CONTENT)");
            }
        }

        return response;
    }

    /* ================ Constructor =========================== */

    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }
}
