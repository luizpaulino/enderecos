package com.monitoramento.usuario.dto.request;

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
public class UsuarioRequest {

    @JsonProperty
    @NotBlank(message = "nome é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @NotBlank(message = "senha é um campo obrigatório e não pode estar em branco")
    private String senha;

    @JsonProperty
    @NotBlank(message = "usuario é um campo obrigatório e não pode estar em branco")
    private String usuario;
}
