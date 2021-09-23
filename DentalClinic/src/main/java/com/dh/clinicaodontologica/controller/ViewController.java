package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Odontologo;
import com.dh.clinicaodontologica.model.Usuario;
import com.dh.clinicaodontologica.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ViewController {

    /* ==================== Atributos =================== */

    private final UsuarioServiceImpl usuarioService;
    Usuario tmpU;

    /* ==================== GET =================== */

    @GetMapping("/")
    public String index(Usuario usuario){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /* ==================== POST =================== */

    @PostMapping("/")
    public ModelAndView login(@ModelAttribute @Valid Usuario usuario, Errors errors){

        ModelAndView modelAndView = new ModelAndView();

        if(errors.hasErrors()){
            modelAndView.setViewName("index");
            modelAndView.addObject("action", "/");
        }else{
            System.out.println(usuario.getUser() + " " + usuario.getPassword());

            if(usuarioService.findUsuarioByLogin(usuario.getUser(), usuario.getPassword()) == null){
                modelAndView.setViewName("Error");
                modelAndView.addObject("action", "/Error");
            }else{
                tmpU = usuarioService.findUsuarioByLogin(usuario.getUser(), usuario.getPassword());
                Odontologo tmp = usuarioService.findOdontologoByUsername(usuario);

                if(tmp != null){
                    if(tmp.isAdmin()){
                        modelAndView.setViewName("OdontologoAdmin");
                        modelAndView.addObject("action", "/OdontologoAdmin");
                    }else{
                        modelAndView.setViewName("Odontologo");
                        modelAndView.addObject("action", "/Odontologo");
                    }
                }else{
                    modelAndView.setViewName("Paciente");
                    modelAndView.addObject("action", "/Paciente");
                }
            }
        }

        return modelAndView;
    }

    @PostMapping("/OdontologoAdmin")
    public ModelAndView OdontologoAdmin(@ModelAttribute Usuario usuario){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("OdontologoAdmin");
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }

    /* ================ Constructor =========================== */

    @Autowired
    public ViewController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }
}
