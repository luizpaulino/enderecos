package com.br.gerenciamento.controller;

import com.br.gerenciamento.form.EnderecoForm;
import com.br.gerenciamento.service.EnderecoService;
import com.br.gerenciamento.view.EnderecoView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/enderecos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoController {

    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoView> adicionarNovoEndereco(@Valid @RequestBody EnderecoForm enderecoForm,
                                                              UriComponentsBuilder uriComponentsBuilder) {

        EnderecoView enderecoView = enderecoService.adicionarNovoEndereco(enderecoForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoView);
    }

}
