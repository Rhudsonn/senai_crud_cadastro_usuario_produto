package com.senai.revisao2.controllers;


import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.mappers.ProdutoMapper;
import com.senai.revisao2.services.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProdutoPageController {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoPageController(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @GetMapping("/produto")
    public String getProduto() {
        return "produto";
    }

    @GetMapping("/cadastrarproduto")
    public String getCadastrarProduto(Model model) {
        model.addAttribute("produto", new ProdutoDto(null,null,null,null,null));
        return "cadastrarproduto";
    }

    @GetMapping("/listaprodutos")
    public String getListaProduto(Model model) {
        List<ProdutoDto> listaProduto = produtoService.obterListaProdutos();
        model.addAttribute("produto", listaProduto);
        return "listaprodutos";
    }

    @GetMapping("/produtoatualizar/{id}")
    public String getProdutoAtualizar(Model model, @PathVariable Long id){
        ProdutoDto produtoDto = produtoService.obterProdutoPorId(id);
        model.addAttribute("produto", produtoDto);
        return "produtoatualizar";
    }

}
