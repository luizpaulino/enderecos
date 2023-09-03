package com.monitoramento.endereco.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@EqualsAndHashCode
public class Endereco {

    @Id
    private String id;
    @UUID
    private String idUsuario;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
