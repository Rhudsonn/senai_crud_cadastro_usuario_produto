package com.senai.revisao2.mappers;


import com.senai.revisao2.dtos.ListarUsuarioDto;
import com.senai.revisao2.dtos.UsuarioDto;
import com.senai.revisao2.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    // Entity-DTO
    public static UsuarioDto entityParaDto(UsuarioEntity usuarioEntity) {
        return  new UsuarioDto(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha()
        );
    }

    // DTO-Entity
    public static UsuarioEntity dtoParaEntity(UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuarioDto.nome());
        usuarioEntity.setEmail(usuarioDto.email());
        usuarioEntity.setSenha(usuarioDto.senha());
        return usuarioEntity;
    }

    // Entity-Dto listarUsuario
    public static ListarUsuarioDto listaUsuarioEntityParaDto(UsuarioEntity usuarioEntity) {
        return  new ListarUsuarioDto(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail()
        );
    }
}
