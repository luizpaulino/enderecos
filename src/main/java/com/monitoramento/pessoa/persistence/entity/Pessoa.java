package com.monitoramento.pessoa.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@Setter
public class Pessoa {

    @Id
    private String id;
    @UUID
    private String idUsuario;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String parentesco;
}
