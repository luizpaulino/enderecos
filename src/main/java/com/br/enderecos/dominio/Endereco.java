package com.br.enderecos.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
