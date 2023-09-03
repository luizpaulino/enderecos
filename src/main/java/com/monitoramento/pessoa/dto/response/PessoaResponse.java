package com.monitoramento.pessoa.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PessoaResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String nome;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @JsonProperty
    private String sexo;
    @JsonProperty
    private String parentesco;
}
