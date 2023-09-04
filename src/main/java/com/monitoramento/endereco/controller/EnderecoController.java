package com.monitoramento.endereco.controller;

import com.monitoramento.endereco.dto.request.VinculoPessoaEnderecoRequest;
import com.monitoramento.endereco.dto.request.EnderecoRequest;
import com.monitoramento.endereco.dto.response.EnderecoResponse;
import com.monitoramento.endereco.service.EnderecoService;
import com.monitoramento.exceptions.PessoaVinculadaException;
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
@RequestMapping("/{idUsuario}/enderecos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoController {

    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponse> adicionarNovoEndereco(@PathVariable String idUsuario,
                                                                  @Valid @RequestBody EnderecoRequest enderecoRequest) {

        EnderecoResponse enderecoResponse = enderecoService.adicionarNovoEndereco(enderecoRequest, idUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoResponse>> listarEnderecosPorFiltro(@PathVariable @Valid String idUsuario,
                                                                           @RequestParam(required = false) String rua,
                                                                           @RequestParam(required = false) String numero,
                                                                           @RequestParam(required = false) String bairro,
                                                                           @RequestParam(required = false) String cidade,
                                                                           @RequestParam(required = false) String estado,
                                                                           Pageable pageable) {

        Page<EnderecoResponse> enderecosFiltadros = enderecoService.filtrarEnderecos(idUsuario, rua, numero, bairro, cidade, estado, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(enderecosFiltadros);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponse> atualizarEndereco(@PathVariable String idUsuario,
                                                            @PathVariable String idEndereco,
                                                            @RequestBody EnderecoRequest enderecoRequest) throws ChangeSetPersister.NotFoundException {

        EnderecoResponse enderecoResponse = enderecoService.atualizarEndereco(enderecoRequest, idEndereco, idUsuario);

        return ResponseEntity.ok(enderecoResponse);
    }

    @PutMapping("/{idEndereco}/vinculo")
    public ResponseEntity<EnderecoResponse> adicionaPessoa(@PathVariable String idUsuario,
                                                           @PathVariable String idEndereco,
                                                           @RequestBody VinculoPessoaEnderecoRequest vinculoPessoaEnderecoRequest) throws ChangeSetPersister.NotFoundException, PessoaVinculadaException {

        EnderecoResponse enderecoResponse = enderecoService.vincularPessoaEndereco(vinculoPessoaEnderecoRequest, idEndereco, idUsuario);

        return ResponseEntity.ok(enderecoResponse);
    }

}
