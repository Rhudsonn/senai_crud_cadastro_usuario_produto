package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.*;
import com.senai.revisao2.execoesPersonalizadas.Duplicado;
import com.senai.revisao2.execoesPersonalizadas.RecursoNaoEncontrado;
import com.senai.revisao2.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService service;
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String realizarLogin(@Valid String email, String senha, Model model, RedirectAttributes redirectAttributes){

        try {
            LogarDto logar = new LogarDto(email,senha);
            LogarDto logarDto = service.realizarLogin(logar);
        }catch (RecursoNaoEncontrado e){
            model.addAttribute("erro","E-mail ou senha invalidos.");
            return "login";
        }

        redirectAttributes.addFlashAttribute("usuario","Bem-vindo");
        return  "redirect:/home";

    }

    @PostMapping("/usuarioinserir")
    public String inserirUsuario(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            return "/usuarioinserir";
        }

        try {
            service.usuarioInserir(usuarioDto);
            redirectAttributes.addAttribute("message","Cadastro realizado com sucesso");
            return "redirect:/home";
        }catch (Duplicado e){
            model.addAttribute("erro",e.getMessage());
            return "/usuarioinserir";
        }
    }


    @PostMapping("/usuarioatualizar")
        public String atualizarUsuario(Model model, @Valid @ModelAttribute("usuario") UsuarioDto usuarioDto, BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "usuarioatualizar";
        }
        try {
            redirectAttributes.addFlashAttribute("mensagem", "Usuario atualizado com sucesso.");
                       service.usuarioAtualizar(usuarioDto);
            return "redirect:/usuariolista";
        }catch (RecursoNaoEncontrado e){
            model.addAttribute("erro","Usuario não encontrado");
        }

        return "redirect:/usuarioatualizar";
            //if (bindingResult.hasErrors()) {
        //                return "usuarioatualizar";
        //            }
        //            redirectAttributes.addFlashAttribute("mensagem", "Usuario atualizado com sucesso.");
        //            service.usuarioAtualizar(usuarioDto);
        //
        //            return "redirect:/usuariolista";
        }



        @DeleteMapping("/usuarioexcluir/{id}")
            public ResponseEntity<String> excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
                service.excluir(id);
                return ResponseEntity.ok().body("Excluido");
           }

}
