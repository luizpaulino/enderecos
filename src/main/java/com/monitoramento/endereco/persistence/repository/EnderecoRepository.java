package com.monitoramento.endereco.persistence.repository;


import com.monitoramento.endereco.persistence.entity.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnderecoRepository  extends MongoRepository<Endereco, String> {
}
