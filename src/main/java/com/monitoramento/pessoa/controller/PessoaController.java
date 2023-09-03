package com.monitoramento.pessoa.controller;

import com.monitoramento.pessoa.dto.request.PessoaRequest;
import com.monitoramento.pessoa.dto.response.PessoaResponse;
import com.monitoramento.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{idUsuario}/pessoas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaController {

    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> adicionarNovaPessoa(@PathVariable String idUsuario,
                                                              @Valid @RequestBody PessoaRequest pessoaRequest) {

        PessoaResponse pessoaResponse = pessoaService.adicionarNovaPessoa(pessoaRequest, idUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaResponse);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> listarPessoasPorFiltro(@PathVariable @Valid String idUsuario,
                                                                       @RequestParam(required = false) String nome,
                                                                       @RequestParam(required = false) String sexo,
                                                                       @RequestParam(required = false) String parentesco,
                                                                       Pageable pageable) {

        Page<PessoaResponse> todasPessoasResponse = pessoaService.filtrarPessoas(idUsuario, nome, sexo, parentesco, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(todasPessoasResponse);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable String idUsuario,
                                                          @PathVariable String idPessoa,
                                                          @RequestBody PessoaRequest pessoaRequest) throws ChangeSetPersister.NotFoundException {

        PessoaResponse pessoaResponse = pessoaService.atualizarPessoa(pessoaRequest, idPessoa, idUsuario);

        return ResponseEntity.status(HttpStatus.OK).body(pessoaResponse);
    }

}