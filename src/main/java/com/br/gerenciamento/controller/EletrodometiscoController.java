package com.br.gerenciamento.controller;

import com.br.gerenciamento.form.EletrodomesticoForm;
import com.br.gerenciamento.service.EletrodomesticoService;
import com.br.gerenciamento.view.EletrodomesticoView;
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
@RequestMapping("/eletrodomesticos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EletrodometiscoController {

    private EletrodomesticoService eletrodomesticoService;
    @PostMapping
    public ResponseEntity<EletrodomesticoView> adicionarNovoEletrodomestico(@Valid @RequestBody EletrodomesticoForm eletrodomesticoForm) {

        EletrodomesticoView eletrodomesticoView = eletrodomesticoService.adicionarNovoEletrodomestico(eletrodomesticoForm);
        return ResponseEntity.status(HttpStatus.OK).body(eletrodomesticoView);
    }
}
