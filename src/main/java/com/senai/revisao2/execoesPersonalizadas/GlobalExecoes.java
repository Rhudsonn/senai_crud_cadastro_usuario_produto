package com.senai.revisao2.execoesPersonalizadas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExecoes {

    //Trata erro de recurso não encontrado.
    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<ErroResponsabilidade> handleNotFoundException(RecursoNaoEncontrado e) {

        ErroResponsabilidade erro = new ErroResponsabilidade(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }


    //Trata erro de duplicado
    @ExceptionHandler(Duplicado.class)
    public ResponseEntity<ErroResponsabilidade> handleDuplicado(Duplicado e) {

        ErroResponsabilidade erro = new ErroResponsabilidade(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


//Trata erro de validação
//@Valid - @NotBlank - @NotNull - Pattern - @Email - @Size
//@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e){
//
//        Map<String, String> erros = new HashMap<>();
//
//        e.getBindingResult().getFieldError().forEach(error -> erros.put(error.getField(),error.getDefaultMessage()));
//
//        return ResponseEntity.badRequest().body(erros);
//    }
}
