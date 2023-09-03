package com.monitoramento.pessoa.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaRequest {

    @JsonProperty
    @NotBlank(message = "nome é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "dataNascimento não deve ser nulo")
    private LocalDate dataNascimento;

    @JsonProperty
    @NotBlank(message = "sexo é um campo obrigatório e não pode estar em branco")
    private String sexo;

    @JsonProperty
    @NotBlank(message = "parentesco é um campo obrigatório e não pode estar em branco")
    private String parentesco;
}
