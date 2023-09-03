package com.monitoramento.endereco.persistence.repository;


import com.monitoramento.endereco.persistence.entity.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EnderecoRepository  extends MongoRepository<Endereco, String> {

    @Query("{'idUsuario' :  ?0, $or: [ {'rua' : ?1}, {'numero': ?2}, {'bairro': ?3}, {'cidade': ?4}, {'estado': ?5}] }")
    Page<Endereco> findEnderecosByIdUsuarioAndRuaOrNumeroOrBairroOrCidadeOrEstado(String idUsuario, String rua, String numero, String bairro,
                                                                   String cidade, String estado, Pageable pageable);

    Page<Endereco> findAllByIdUsuario(String idUsuario, Pageable pageable);

    @Query("{'id' :  ?0, 'idUsuario' : ?1}")
    Optional<Endereco> findEnderecoByIdUsuario(String id, String idUsuario);
}
