package com.monitoramento.endereco.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VinculoPessoaEnderecoRequest {

    @JsonProperty
    @NotBlank(message = "idPessoa é um campo obrigatório e não pode estar em branco")
    private String idPessoa;
}
