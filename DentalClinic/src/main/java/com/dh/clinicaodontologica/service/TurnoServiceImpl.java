package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.dto.TurnoDTO;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.model.Turno;
import com.dh.clinicaodontologica.repository.ITurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements IModelService<Turno> {

    /* ================= Atributos ====================== */

    private ITurnoRepository turnoRepository;
    private PacienteServiceImpl pacienteService;
    private OdontologoServiceImpl odontologoService;
    private static final Logger logger = Logger.getLogger(TurnoServiceImpl.class);

    /* ================= Getters y Setters ====================== */

    public ITurnoRepository getTurnoRepository() {
        return turnoRepository;
    }

    public void setTurnoRepository(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public PacienteServiceImpl getPacienteService() {
        return pacienteService;
    }

    public void setPacienteService(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    public OdontologoServiceImpl getOdontologoService() {
        return odontologoService;
    }

    public void setOdontologoService(OdontologoServiceImpl odontologoService) {
        this.odontologoService = odontologoService;
    }

    /* ================= Métodos ====================== */

    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> findById(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public String delete(Long id) {
        if(turnoRepository.findById(id).isPresent()){
            turnoRepository.deleteById(id);
            logger.debug("Dato eliminado correctamente en la entidad Turnos");
            return "Turno with id: " + id + " was deleted";
        } else{
            logger.debug("Eliminación de campos en la entidad Turnos fallida -> ID no existente (NOT_FOUND)");
            return "Turno with id:" + id + " don't exist";
        }
    }

    @Override
    public String update(Turno turno) {

        Turno turnoTmp = turnoRepository.findById(turno.getId()).get();
        Odontologo odTmp = odontologoService.findOdontologoByFullname(turno.getOdontologo().getUsuario().getNombre(),turno.getOdontologo().getUsuario().getApellido());
        Paciente pcTmp = pacienteService.findPacienteByFullname(turno.getPaciente().getUsuario().getNombre(),turno.getPaciente().getUsuario().getApellido());

        turnoTmp.setOdontologo(odTmp);
        turnoTmp.setPaciente(pcTmp);
        turnoTmp.setFechaTurno(turno.getFechaTurno());
        turnoTmp.setHoraTurno(turno.getHoraTurno());
        turnoRepository.save(turnoTmp);

        return "Turno with id: " + turno.getId() + " was updated";
    }

    /* ============ Useful functions =============*/

    public List<Turno> findTurnosByPaciente(String UserPaciente){
        return turnoRepository.findTurnosByPaciente(UserPaciente);
    }

    public List<Turno> findTurnosByOdontologo(String UserOdontologo){
        return turnoRepository.findTurnosByOdontologo(UserOdontologo);
    }

    public TurnoDTO mapToDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setNombreOdontologo(turno.getOdontologo().getUsuario().getApellido() + ", " + turno.getOdontologo().getUsuario().getNombre());
        turnoDTO.setNombrePaciente(turno.getPaciente().getUsuario().getApellido() + ", " + turno.getPaciente().getUsuario().getNombre());
        turnoDTO.setFechaTurno(turno.getFechaTurno());
        turnoDTO.setHoraTurno(turno.getHoraTurno());
        logger.debug("Entidad Turno mapeada a TurnoDTO");
        return turnoDTO;
    }

    public Turno mapToEntity(TurnoDTO turnoDTO){

        Turno turno = null;
        String[] nombreOdontologo = turnoDTO.getNombreOdontologo().split(", ");
        String[] nombrePaciente = turnoDTO.getNombrePaciente().split(", ");

        Odontologo odTmp = odontologoService.findOdontologoByFullname(nombreOdontologo[1],nombreOdontologo[0]);
        Paciente pcTmp = pacienteService.findPacienteByFullname(nombrePaciente[1],nombrePaciente[0]);


        if(turnoDTO.getId() != null){
            if(turnoRepository.findById(turnoDTO.getId()).isPresent()){
                turno = turnoRepository.findById(turnoDTO.getId()).get();

                turno.setOdontologo(odTmp);
                turno.setPaciente(pcTmp);

                turno.setFechaTurno(turnoDTO.getFechaTurno());
                turno.setHoraTurno(turnoDTO.getHoraTurno());
            }
        }else{
            return new Turno(pcTmp,odTmp,turnoDTO.getFechaTurno(),turnoDTO.getHoraTurno());
        }

        logger.debug("Entidad TurnoDTO mapeada a Turno");
        return turno;
    }

    public List<Turno> listaTurnosSemanales(){

        LocalDate hoy = LocalDate.now();
        LocalDate plusSevenDays = hoy.plusDays(7);

        return turnoRepository.listaTurnosSemanales(hoy.toString(),plusSevenDays.toString());
    }


    /* ================= Constructor ====================== */

    @Autowired
    public TurnoServiceImpl(ITurnoRepository turnoRepository, PacienteServiceImpl pacienteService, OdontologoServiceImpl odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }
}
