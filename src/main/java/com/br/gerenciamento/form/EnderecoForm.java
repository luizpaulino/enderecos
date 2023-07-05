package com.br.gerenciamento.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnderecoForm {

    @JsonProperty
    @NotBlank(message = "rua é um campo obrigatório e não pode estar em branco")
    private String rua;

    @JsonProperty
    @NotBlank(message = "numero é um campo obrigatório e não pode estar em branco")
    private String numero;

    @JsonProperty
    @NotBlank(message = "bairro é um campo obrigatório e não pode estar em branco")
    private String bairro;

    @JsonProperty
    @NotBlank(message = "cidade é um campo obrigatório e não pode estar em branco")
    private String cidade;

    @JsonProperty
    @NotBlank(message = "estado é um campo obrigatório e não pode estar em branco")
    private String estado;

}
