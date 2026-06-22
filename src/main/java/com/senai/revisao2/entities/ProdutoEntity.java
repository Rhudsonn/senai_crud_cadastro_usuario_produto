package com.senai.revisao2.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class ProdutoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String nomeProduto;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quantidade;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id, String nomeProduto, String descricao, BigDecimal preco, Integer quantidade) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
