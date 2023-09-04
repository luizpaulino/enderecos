package com.monitoramento.usuario.controller;

import com.monitoramento.exceptions.UsuarioJaExisteException;
import com.monitoramento.usuario.dto.request.UsuarioRequest;
import com.monitoramento.usuario.dto.response.UsuarioResponse;
import com.monitoramento.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> adicionarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) throws UsuarioJaExisteException {

        UsuarioResponse pessoaResponse = usuarioService.adicionarNovaPessoa(usuarioRequest);

        return ResponseEntity.ok(pessoaResponse);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> atualizarPessoa(@PathVariable String idUsuario) throws ChangeSetPersister.NotFoundException {

        usuarioService.deletarUsuario(idUsuario);

        return ResponseEntity.accepted().build();
    }

}
