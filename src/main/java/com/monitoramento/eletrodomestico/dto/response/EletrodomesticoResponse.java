package com.monitoramento.eletrodomestico.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EletrodomesticoResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private String modelo;

    @JsonProperty
    private BigDecimal potencia;
}
