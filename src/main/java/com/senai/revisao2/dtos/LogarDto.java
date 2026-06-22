package com.senai.revisao2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LogarDto(

        @NotBlank(message = "Email é obrigatório")
        @Email
        String email,

        @NotBlank(message = "Senha é obrigatório")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial")
        String senha
) {
}
