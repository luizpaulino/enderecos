package com.br.gerenciamento.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class Eletrodomestico {

    private String nome;
    private String modelo;
    private BigDecimal potencia;
}
