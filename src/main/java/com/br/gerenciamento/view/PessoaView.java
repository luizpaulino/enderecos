package com.br.gerenciamento.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaView {

    @JsonProperty
    @NotBlank(message = "nome é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonProperty
    @NotBlank(message = "sexo é um campo obrigatório e não pode estar em branco")
    private String sexo;

    @JsonProperty
    @NotBlank(message = "parentesco é um campo obrigatório e não pode estar em branco")
    private String parentesco;
}
