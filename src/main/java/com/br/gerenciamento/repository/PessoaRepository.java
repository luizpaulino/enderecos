package com.br.gerenciamento.repository;

import com.br.gerenciamento.dominio.Pessoa;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PessoaRepository {

    private Set<Pessoa> pessoaSet;

    public PessoaRepository() {
        pessoaSet = new HashSet<>();
    }

    public void salvar(Pessoa pessoa) {
        pessoaSet.add(pessoa);
    }
}
