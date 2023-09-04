package com.monitoramento.usuario.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;
    @UUID
    private String nome;
    private String senha;
    private String usuario;
}
