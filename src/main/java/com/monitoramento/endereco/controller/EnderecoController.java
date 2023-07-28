package com.monitoramento.endereco.controller;

import com.monitoramento.endereco.dto.request.EnderecoRequest;
import com.monitoramento.endereco.dto.response.EnderecoResponse;
import com.monitoramento.endereco.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/{idUsuario}/enderecos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoController {

    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponse> adicionarNovoEndereco(@PathVariable String idUsuario,
                                                                  @Valid @RequestBody EnderecoRequest enderecoRequest,
                                                                  UriComponentsBuilder uriComponentsBuilder) {

        enderecoRequest.setIdUsuario(idUsuario);

        EnderecoResponse enderecoResponse = enderecoService.adicionarNovoEndereco(enderecoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoResponse);
    }

}
