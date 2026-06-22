package com.senai.revisao2.services;

import com.senai.revisao2.dtos.ListarUsuarioDto;
import com.senai.revisao2.dtos.LogarDto;
import com.senai.revisao2.dtos.UsuarioDto;
import com.senai.revisao2.entities.UsuarioEntity;
import com.senai.revisao2.execoesPersonalizadas.Duplicado;
import com.senai.revisao2.execoesPersonalizadas.RecursoNaoEncontrado;
import com.senai.revisao2.mappers.UsuarioMapper;
import com.senai.revisao2.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Realizar Login
    public LogarDto realizarLogin(LogarDto  logarDto){

        repository.findByEmailAndSenha(logarDto.email(), logarDto.senha())
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuario não existe"));

        if (!repository.existsByEmail(logarDto.email()) &&  repository.existsBySenha(logarDto.senha())) {
            throw new RecursoNaoEncontrado("Email ou senha incorreto");
        }
        return logarDto;
    }

    //Listar Usuarios
    public List<ListarUsuarioDto> obterListaUsuarios(){

        List<UsuarioEntity> listaUsuario = repository.findAll();
        List<ListarUsuarioDto> listaDto = new ArrayList<>();

        for (UsuarioEntity usuario : listaUsuario){
            listaDto.add(UsuarioMapper.listaUsuarioEntityParaDto(usuario));
        }
        return listaDto;
    }

    //Cadastro de usuario
    public void usuarioInserir(UsuarioDto usuarioDto){
        if (repository.existsByEmail(usuarioDto.email())){
            throw new Duplicado("Usuario ja existente");
        }
        repository.save(UsuarioMapper.dtoParaEntity(usuarioDto));
    }

    public UsuarioDto obterUsuarioPorId(Long id){
        Optional<UsuarioEntity> usuarioOP = repository.findById(id);

                UsuarioDto usuarioDto = UsuarioMapper.entityParaDto(repository.findById(id)
                                .orElseThrow(()-> new RecursoNaoEncontrado("Usuario não encontrado")));

                if (usuarioOP.isPresent()){
                    //--Converte o entity para dto
                    return UsuarioMapper.entityParaDto(usuarioOP.get());
                }
                throw new RecursoNaoEncontrado("Usuario não encontrado com o id informado"+ id);
            }



    public void usuarioAtualizar(UsuarioDto usuarioDto){

        Optional<UsuarioEntity> usuarioOP = repository.findById(usuarioDto.id());

        if (usuarioOP.isPresent()){

                    // usuarioOP.get() --> usuario com os dados do banco de dados
                    // usuarioDto --> dados do usuário que vieram do formulário

                   UsuarioEntity usuario = usuarioOP.get();
                   usuario.setNome(usuarioDto.nome());
                   usuario.setEmail(usuarioDto.email());

                    //--Não fazer a atualização da senha quando não vier
                   if (!usuarioDto.senha().isEmpty()){
                       usuario.setSenha(usuarioDto.senha());
                   }
                    repository.save(usuario);
                }

    }



    public void excluir(Long id) {
        repository.deleteById(id);
    }


}
