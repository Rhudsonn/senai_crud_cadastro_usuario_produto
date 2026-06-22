package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.ListarUsuarioDto;
import com.senai.revisao2.dtos.UsuarioDto;
import com.senai.revisao2.mappers.UsuarioMapper;
import com.senai.revisao2.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {

    private final UsuarioService service;;
    private final UsuarioMapper mapper;

    public PageController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }


    @GetMapping("/usuariolista")
    public String getUsuarioLista(Model model) {

        List<ListarUsuarioDto> usuarioDtoLita = service.obterListaUsuarios();
        model.addAttribute("usuarios", usuarioDtoLita);
        return "usuariolista";
    }


    @GetMapping("/usuarioinserir")
    public String getUsuarioInserir(Model model) {
        model.addAttribute("usuario", new UsuarioDto(null,null,null,null));
        return "usuarioinserir";
    }


    @GetMapping("/usuarioatualizar/{id}")
    public String getUsuarioAtualizar(Model model, @PathVariable Long id) {

        //--buscar os valores do banco de dados
        UsuarioDto dto = service.obterUsuarioPorId(id);

        //--adicionar o DTO com os dados do usuario em memoria (no model)
        model.addAttribute("usuario", dto);

        return "usuarioatualizar";
    }





}


