package com.senai.revisao2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoDto(

        Long id,

        @NotBlank(message = "nome é obrigatório")
        @Size(min = 3, max = 100)
        String nomeProduto,

        @NotBlank(message = "Descrição do produto é obrigatório")
        @Size(min = 3, max = 255)
        String descricao,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser Valido")
        BigDecimal preco,


        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser maior que 0")
        Integer quantidade
) {
}
