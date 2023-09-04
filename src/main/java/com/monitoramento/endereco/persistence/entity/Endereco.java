package com.monitoramento.endereco.persistence.entity;

import com.monitoramento.pessoa.persistence.entity.Pessoa;
import lombok.*;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Endereco {

    public Endereco(String id, String idUsuario, String rua, String numero, String bairro, String cidade, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Id
    private String id;
    @UUID
    private String idUsuario;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private List<String> pessoas = new ArrayList<>();
}
