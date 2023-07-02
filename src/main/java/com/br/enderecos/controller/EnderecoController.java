package com.br.enderecos.controller;

import com.br.enderecos.form.EnderecoForm;
import com.br.enderecos.service.EnderecoService;
import com.br.enderecos.view.EnderecoView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/enderecos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoController {

    EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoView> adicionarNovoEndereco(@Valid @RequestBody EnderecoForm enderecoForm,
                                                              UriComponentsBuilder uriComponentsBuilder) {

        EnderecoView enderecoView = enderecoService.adicionarNovoEndereco(enderecoForm);

        return ResponseEntity.created(uriComponentsBuilder.path("/enderecos").build().toUri()).body(enderecoView);
    }

}
