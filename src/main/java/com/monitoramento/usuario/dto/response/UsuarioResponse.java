package com.monitoramento.usuario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String usuario;

}
