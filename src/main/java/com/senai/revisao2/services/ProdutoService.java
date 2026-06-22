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
    private final UsuarioMapper usuarioMapper;

    public ProdutoService(ProdutoRepository repository, UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.usuarioMapper = usuarioMapper;
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
        Optional<ProdutoEntity> produto = repository.findByIdAndNomeProduto(produtoDto.id(), produtoDto.nomeProduto());

        if (produto.isPresent()){
            ProdutoEntity produtoEntity = produto.get();
            produtoEntity.setNomeProduto(produtoDto.nomeProduto());
            produtoEntity.setDescricao(produtoDto.descricao());
            produtoEntity.setPreco(produtoDto.preco());
            produtoEntity.setQuantidade(produtoDto.quantidade());
            repository.save(produtoEntity);
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
