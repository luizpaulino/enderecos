package com.monitoramento.endereco.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponse {
    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;
}
