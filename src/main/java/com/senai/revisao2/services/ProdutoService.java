package com.senai.revisao2.services;

import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.entities.ProdutoEntity;
import com.senai.revisao2.execoesPersonalizadas.Duplicado;
import com.senai.revisao2.execoesPersonalizadas.RecursoNaoEncontrado;
import com.senai.revisao2.mappers.ProdutoMapper;
import com.senai.revisao2.mappers.UsuarioMapper;
import com.senai.revisao2.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Cadastro de produto
    public void cadastroProduto(ProdutoDto produtoDto){
        if (repository.existsByNomeProduto(produtoDto.nomeProduto())){
            throw new Duplicado("Produto ja existente");
        }
        repository.save(ProdutoMapper.dtoParaEntity(produtoDto));
    }

    //Listar produto
    public List<ProdutoDto> obterListaProdutos(){
        List<ProdutoEntity> listaProdutos = repository.findAll();
        List<ProdutoDto> listaDto = new ArrayList<>();
        for (ProdutoEntity produtoEntity : listaProdutos){
            listaDto.add(ProdutoMapper.entityParaDto(produtoEntity));
        }
        return listaDto;
    }


    //Atualizar produto
    public void produtoAtualizar(ProdutoDto produtoDto){
        Optional<ProdutoEntity> produto = repository.findById(produtoDto.id());

        if (produto.isPresent()){
            ProdutoEntity produto1 = produto.get();
            produto1.setNomeProduto(produtoDto.nomeProduto());
            produto1.setDescricao(produtoDto.descricao());
            produto1.setPreco(produtoDto.preco());
            produto1.setQuantidade(produtoDto.quantidade());
            repository.save(produto1);
        }

    }

    //Obter lista de produtos por id
    public ProdutoDto obterProdutoPorId(Long id){
        Optional<ProdutoEntity> produto = repository.findById(id);

        ProdutoDto produtoDto = ProdutoMapper.entityParaDto(repository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontrado("Produto não encontrado")));

        if (produto.isPresent()){
            return ProdutoMapper.entityParaDto(produto.get());
        }
        throw new RecursoNaoEncontrado("Não encontrado");

    }

    //Excluir produto
    public void excluir(Long id) {
        repository.deleteById(id);
    }


}
