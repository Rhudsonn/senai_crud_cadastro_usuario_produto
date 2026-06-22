package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.execoesPersonalizadas.Duplicado;
import com.senai.revisao2.services.ProdutoService;
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
public class ProdutoController {

    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrarproduto")
    public String cadastrarProduto(@Valid @ModelAttribute("produto") ProdutoDto produtoDto, BindingResult result,
                                   RedirectAttributes attributes, Model model){
        if(result.hasErrors()){
            return "cadastrarproduto";
        }
        try {
            produtoService.cadastroProduto(produtoDto);
            attributes.addAttribute("mensagem","Cadastro realizado com sucesso");
            return "redirect:/listaprodutos";
        }catch (Duplicado e){
            model.addAttribute("erro",e.getMessage());
            return "cadastrarproduto";
        }
    }


    @PostMapping("/produtoatualizar")
    public String atualizarProduto(Model model,@Valid @ModelAttribute("produto") ProdutoDto produtoDto, BindingResult bindingResult,RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            return "cadastrarproduto";
        }
        attributes.addFlashAttribute("message", "Produto cadastrado com sucesso");
        produtoService.produtoAtualizar(produtoDto);
        return "redirect:/listaprodutos";
    }


    @DeleteMapping("/produtoexcluir/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable Long id){
        produtoService.excluir(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso");
    }



}
