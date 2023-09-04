package com.monitoramento.eletrodomestico.persistence.repository;

import com.monitoramento.eletrodomestico.persistence.entity.Eletrodomestico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EletrodomesticoRepository extends MongoRepository<Eletrodomestico, String> {

    @Query("{'idUsuario' :  ?0, $or: [ {'nome' : ?1}, {'modelo': ?2}, {'potencia': ?3}] }")
    Page<Eletrodomestico> findEletrodomesticosByIdUsuarioAndNomeOrModeloOrPotencia(String idUsuario,
                                                                                   String nome,
                                                                                   String modelo,
                                                                                   String potencia,
                                                                                   Pageable pageable);

    Page<Eletrodomestico> findAllByIdUsuario(String idUsuario, Pageable pageable);

    @Query("{'id': ?0, 'idUsuario' :  ?1}")
    Optional<Eletrodomestico> findEletrodomesticoByIdAndIdUsuario(String id, String idUsuario);
}
