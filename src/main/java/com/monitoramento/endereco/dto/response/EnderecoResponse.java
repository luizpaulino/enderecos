package com.monitoramento.endereco.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnderecoResponse {

    @JsonProperty
    private String id;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private List<String> pessoas;
}
