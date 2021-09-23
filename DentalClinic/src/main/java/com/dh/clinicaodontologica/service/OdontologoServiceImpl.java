package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.controller.OdontologoController;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.repository.IOdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoServiceImpl implements IModelService<Odontologo>{

    /* =============== Atributos =============*/

    private IOdontologoRepository odontologoRepository;
    private static final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);

    /* =============== Getters y Setters =============*/

    public IOdontologoRepository getOdontologoRepository() {
        return odontologoRepository;
    }

    public void setOdontologoRepository(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    /* =============== Métodos =============*/

    @Override
    public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> findById(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public Odontologo save(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public String delete(Long id) {
        if(odontologoRepository.findById(id).isPresent()){
            odontologoRepository.deleteById(id);
            logger.debug("Dato eliminado correctamente en la entidad Odontologos");
            return "Odontologo with id: " + id + " was deleted";
        } else{
            logger.debug("Eliminación de campos en la entidad Odontologos fallida -> ID no existente (NOT_FOUND)");
            return "Odontologo with id:" + id + " don't exist";
        }
    }

    @Override
    public String update(Odontologo odontologo) {
        Odontologo odontologoTmp = odontologoRepository.findById(odontologo.getId()).get();
        odontologoTmp.setNumeroMatricula(odontologo.getNumeroMatricula());
        odontologoTmp.setAdmin(odontologo.isAdmin());
        odontologoTmp.setUsuario(odontologo.getUsuario());
        odontologoTmp.setDomicilio(odontologo.getDomicilio());
        odontologoRepository.save(odontologoTmp);

        return "Odontologo with id: " + odontologo.getId() + " was updated";
    }

    /* ======= Useful functions =============*/

    public Usuario findUsuarioByUsername(String username){
        logger.debug("Búsqueda en la entidad Usuarios filtrada por usuario");
        return odontologoRepository.findUsuarioByUsername(username);
    }

    public List<Object> listUsernames(){
        logger.debug("Listado general en la entidad Usuarios filtrada por usuario");
        return odontologoRepository.listUsernames();
    }

    public Odontologo findOdontologoByNumeroMatricula(Integer numeroMatricula){
        logger.debug("Búsqueda en la entidad Odontologos filtrada por Numero de matrícula");
        return odontologoRepository.findOdontologoByNumeroMatricula(numeroMatricula);
    }

    public Odontologo findOdontologoByUsername(String Username){
        logger.debug("Búsqueda en la entidad Odontologos filtrada por usuario");
        return odontologoRepository.findOdontologoByUsername(Username);
    }

    public Odontologo findOdontologoByFullname(String Name, String Lastname){
        logger.debug("Búsqueda en la entidad Odontologos filtrada por Nombre y Apellido");
        return odontologoRepository.findOdontologoByFullname(Name,Lastname);
    }

    /* =============== Constructor =============*/

    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
}
