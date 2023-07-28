package com.monitoramento.pessoa.persistence.repository;

import com.monitoramento.pessoa.persistence.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    @Query("{'idUsuario' :  ?0, $or: [ {'nome' : ?1}, {'parentesco': ?2}, {'sexo': ?3}] }")
    Page<Pessoa> findPessoasByIdUsuarioAndNomeOrParentescoOrSexo(String idUsuario,
                                                                 String nome,
                                                                 String parentesco,
                                                                 String sexo,
                                                                 Pageable pageable);

    Page<Pessoa> findAllByIdUsuario(String idUsuario, Pageable pageable);
}
