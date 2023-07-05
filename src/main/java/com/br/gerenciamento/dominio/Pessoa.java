package com.br.gerenciamento.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String parentesco;
}
