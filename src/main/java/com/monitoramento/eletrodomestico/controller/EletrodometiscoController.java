package com.monitoramento.eletrodomestico.controller;

import com.monitoramento.eletrodomestico.dto.request.EletrodomesticoRequest;
import com.monitoramento.eletrodomestico.service.EletrodomesticoService;
import com.monitoramento.eletrodomestico.dto.response.EletrodomesticoResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
                                                                                @Valid @RequestBody EletrodomesticoRequest eletrodomesticoRequest) {

        eletrodomesticoRequest.setIdUsuario(idUsuario);
        EletrodomesticoResponse eletrodomesticoResponse = eletrodomesticoService.adicionarNovoEletrodomestico(eletrodomesticoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(eletrodomesticoResponse);
    }
}
