package com.monitoramento.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ArgumentExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> argumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("campos_invalidos", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ResponseBody
    @ExceptionHandler(PessoaVinculadaException.class)
    protected ResponseEntity<Object> vinculoPessoaInvalido(PessoaVinculadaException ex) {

        Map<String, String> body = new HashMap<>();

        body.put("error", "Solicitação de alteração de vinculo da pessoa inválida");

        return ResponseEntity.badRequest().body(body);
    }
    @ResponseBody
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    protected ResponseEntity<Object> registroNaoEncontrado(ChangeSetPersister.NotFoundException ex) {

        Map<String, String> body = new HashMap<>();

        body.put("error", "Registro não encontrado");

        return ResponseEntity.badRequest().body(body);
    }

}
