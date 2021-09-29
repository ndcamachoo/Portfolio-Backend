package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.repository.IPacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IModelService<Paciente>{

    /* =============== Atributos =============*/

    private IPacienteRepository pacienteRepository;
    private static final Logger logger = Logger.getLogger(PacienteServiceImpl.class);

    /* =============== Getters y Setters =============*/

    public IPacienteRepository getPacienteRepository() {
        return pacienteRepository;
    }

    public void setPacienteRepository(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /* =============== Métodos =============*/

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public String delete(Long id) {
        if(pacienteRepository.findById(id).isPresent()){
            pacienteRepository.deleteById(id);
            logger.debug("Dato eliminado correctamente en la entidad Pacientes");
            return "Paciente with id: " + id + " was deleted";
        } else{
            logger.debug("Eliminación de campos en la entidad Pacientes fallida -> ID no existente (NOT_FOUND)");
            return "Paciente with id:" + id + " don't exist";
        }
    }

    @Override
    public String update(Paciente paciente) {
        Paciente pacienteTmp = pacienteRepository.findById(paciente.getId()).get();
        pacienteTmp.setDNI(paciente.getDNI());
        pacienteTmp.setFechaIngreso(paciente.getFechaIngreso());
        pacienteTmp.setUsuario(paciente.getUsuario());
        pacienteTmp.setDomicilio(paciente.getDomicilio());
        pacienteRepository.save(pacienteTmp);
        logger.debug("Actualización de campos en la entidad Pacientes -> ID:" + paciente.getId());
        return "Paciente with id: " + paciente.getId() + " was updated";
    }

    /* ======= Useful functions =============*/


    public Paciente findPacienteByDNI(Integer DNI){
        logger.debug("Búsqueda en la entidad Pacientes filtrada por DNI");
        return pacienteRepository.findPacienteByDNI(DNI);
    }

    public Paciente findPacienteByFullname(String Name, String Lastname){
        logger.debug("Búsqueda en la entidad Pacientes filtrada por Nombre y Apellido");
        return pacienteRepository.findPacienteByFullname(Name,Lastname);
    }

    /* =============== Constructor =============*/

    @Autowired
    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
}
