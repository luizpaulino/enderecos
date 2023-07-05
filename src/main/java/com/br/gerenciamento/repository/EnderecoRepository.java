package com.br.gerenciamento.repository;

import com.br.gerenciamento.dominio.Endereco;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class EnderecoRepository {

    private Set<Endereco> enderecoSet;

    public EnderecoRepository() {
        enderecoSet = new HashSet<>();
    }

    public void salvar(Endereco endereco) {
        enderecoSet.add(endereco);
    }

}
