package com.monitoramento.eletrodomestico.persistence.entity;

import lombok.*;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Eletrodomestico {

    @Id
    private String id;
    @UUID
    private String idUsuario;
    private String nome;
    private String modelo;
    private BigDecimal potencia;
}
