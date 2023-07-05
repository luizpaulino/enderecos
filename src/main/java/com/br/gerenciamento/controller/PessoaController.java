package com.br.gerenciamento.controller;

import com.br.gerenciamento.form.PessoaForm;
import com.br.gerenciamento.service.PessoaService;
import com.br.gerenciamento.view.PessoaView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaController {

    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaView> adicionarNovaPessoa(@Valid @RequestBody PessoaForm pessoaForm) {

        PessoaView pessoaView = pessoaService.adicionarNovaPessoa(pessoaForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaView);
    }
}
