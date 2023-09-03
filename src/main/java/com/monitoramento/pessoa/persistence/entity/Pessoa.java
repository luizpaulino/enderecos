package com.monitoramento.pessoa.persistence.entity;

import lombok.*;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Pessoa {

    public Pessoa(String id, String idUsuario, String nome, LocalDate dataNascimento, String sexo, String parentesco) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.parentesco = parentesco;
    }

    @Id
    private String id;
    @UUID
    private String idUsuario;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String parentesco;
    private Boolean vinculada = false;
}
