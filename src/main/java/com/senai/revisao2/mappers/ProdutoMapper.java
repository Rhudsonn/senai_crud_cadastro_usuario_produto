package com.senai.revisao2.mappers;

import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.entities.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    // Entity-DTO
    public static ProdutoDto entityParaDto(ProdutoEntity produtoEntity) {
        return  new ProdutoDto(
                produtoEntity.getId(),
                produtoEntity.getNomeProduto(),
                produtoEntity.getDescricao(),
                produtoEntity.getPreco(),
                produtoEntity.getQuantidade()
        );
    }

    // DTO-Entity
    public static ProdutoEntity dtoParaEntity(ProdutoDto produtoDto) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNomeProduto(produtoDto.nomeProduto());
        produtoEntity.setDescricao(produtoDto.descricao());
        produtoEntity.setPreco(produtoDto.preco());
        produtoEntity.setQuantidade(produtoDto.quantidade());
        return produtoEntity;
    }
}
