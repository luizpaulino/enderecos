package com.br.gerenciamento.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class EletrodomesticoForm {

    @JsonProperty
    @NotBlank(message = "nome é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @NotBlank(message = "modelo é um campo obrigatório e não pode estar em branco")
    private String modelo;

    @JsonProperty
    @Positive(message = "potencia é um campo que deve ser um número positivo")
    @NotNull(message = "potencia é um campo obrigatório e não pode ser null")
    private BigDecimal potencia;
}
