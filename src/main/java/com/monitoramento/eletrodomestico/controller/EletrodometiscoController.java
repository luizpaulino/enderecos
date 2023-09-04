package com.monitoramento.eletrodomestico.controller;

import com.monitoramento.eletrodomestico.dto.request.EletrodomesticoRequest;
import com.monitoramento.eletrodomestico.dto.response.EletrodomesticoResponse;
import com.monitoramento.eletrodomestico.service.EletrodomesticoService;
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
@RequestMapping("/{idUsuario}/eletrodomesticos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EletrodometiscoController {

    private EletrodomesticoService eletrodomesticoService;

    @PostMapping
    public ResponseEntity<EletrodomesticoResponse> adicionarNovoEletrodomestico(@PathVariable String idUsuario,
                                                                                @Valid @RequestBody EletrodomesticoRequest eletrodomesticoRequest) throws ChangeSetPersister.NotFoundException {
        EletrodomesticoResponse eletrodomesticoResponse = eletrodomesticoService.adicionarNovoEletrodomestico(eletrodomesticoRequest, idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(eletrodomesticoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<EletrodomesticoResponse>> listarEletrodomesticosPorFiltro(@PathVariable @Valid String idUsuario,
                                                                                         @RequestParam(required = false) String nome,
                                                                                         @RequestParam(required = false) String modelo,
                                                                                         @RequestParam(required = false) String potencia,
                                                                                         Pageable pageable) throws ChangeSetPersister.NotFoundException {

        Page<EletrodomesticoResponse> eletrodomesticosFiltrados = eletrodomesticoService.filtrarEletrodomesticos(idUsuario, nome, modelo, potencia, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(eletrodomesticosFiltrados);
    }

    @PutMapping("/{idEletrodomestico}")
    public ResponseEntity<EletrodomesticoResponse> atualizarPessoa(@PathVariable String idUsuario,
                                                          @PathVariable String idEletrodomestico,
                                                          @RequestBody EletrodomesticoRequest eletrodomesticoRequest) throws ChangeSetPersister.NotFoundException {

        EletrodomesticoResponse eletrodomesticoResponse = eletrodomesticoService.atualizarEletrodomestico(eletrodomesticoRequest, idEletrodomestico, idUsuario);

        return ResponseEntity.ok(eletrodomesticoResponse);
    }

    @DeleteMapping("/{idEletrodomestico}")
    public ResponseEntity atualizarPessoa(@PathVariable String idUsuario,
                                          @PathVariable String idEletrodomestico) throws ChangeSetPersister.NotFoundException {

        eletrodomesticoService.removerEletrodomestico(idEletrodomestico, idUsuario);

        return ResponseEntity.accepted().build();
    }
}
