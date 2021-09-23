package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.dto.UsuarioDTO;
import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.repository.IUsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    /* =============== Atributos =============*/

    private final IUsuarioRepository usuarioRepository;
    private final OdontologoServiceImpl odontologoService;
    private static final Logger logger = Logger.getLogger(UsuarioServiceImpl.class);

    /* =============== Métodos ================ */

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUsuarioById(Long id){
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findUsuarioByUsername(String Username) {
        return usuarioRepository.findUsuarioByUser(Username);
    }


    @Override
    public Usuario findUsuarioByLogin(String Username, String Password) {
        return usuarioRepository.findUsuarioByLogin(Username.toLowerCase(), Password);
    }

    public String update(Usuario usuario) {
        Usuario usuarioTmp = usuarioRepository.findById(usuario.getId()).get();
        usuarioTmp.setNombre(usuario.getNombre());
        usuarioTmp.setApellido(usuario.getApellido());
        usuarioTmp.setUser(usuario.getUser());
        usuarioTmp.setPassword(usuario.getPassword());
        usuarioRepository.save(usuarioTmp);
        return "User with id: " + usuario.getId() + " was updated";
    }

    public Odontologo findOdontologoByUsername(Usuario usuario){
        return odontologoService.findOdontologoByUsername(usuario.getUser());
    }

    public Usuario findUsuarioByNombreAndApellido(String Nombre, String Apellido){
        return usuarioRepository.findUsuarioByNombreAndApellido(Nombre, Apellido);
    }

    /* ============ Useful functions =============*/

    public UsuarioDTO mapToDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setFullname(usuario.getApellido() + ", " + usuario.getNombre());
        usuarioDTO.setUser(usuario.getUser());
        logger.debug("Entidad Usuario mapeada a UsuarioDTO");
        return usuarioDTO;
    }

    public Usuario mapToEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = findUsuarioByUsername(usuarioDTO.getUser());
        String[] nombreUsuario = usuarioDTO.getFullname().split(", ");
        usuario.setNombre(nombreUsuario[1]);
        usuario.setApellido(nombreUsuario[0]);
        logger.debug("Entidad UsuarioDTO mapeada a Usuario");
        return usuario;
    }

    /* =============== Constructor ================ */

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, OdontologoServiceImpl odontologoService) {
        this.usuarioRepository = usuarioRepository;
        this.odontologoService = odontologoService;
    }
}
