package com.senai.revisao2.execoesPersonalizadas;

import java.time.LocalDateTime;

public class ErroResponsabilidade {

    //Data e hora do erro
    private LocalDateTime dataHora;

    //Codigo HTTP
    private Integer status;

    //Mensagem do erro
    private String mensagem;

    public ErroResponsabilidade() {
    }

    public ErroResponsabilidade(LocalDateTime dataHora, Integer status, String mensagem) {
        this.dataHora = dataHora;
        this.status = status;
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
