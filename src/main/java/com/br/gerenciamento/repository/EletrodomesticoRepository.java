package com.br.gerenciamento.repository;

import com.br.gerenciamento.dominio.Eletrodomestico;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class EletrodomesticoRepository {
    private Set<Eletrodomestico> eletrodomesticoSet;

    public EletrodomesticoRepository() {
        eletrodomesticoSet = new HashSet<>();
    }

    public void salvar(Eletrodomestico eletrodomestico) {
        eletrodomesticoSet.add(eletrodomestico);
    }
}
