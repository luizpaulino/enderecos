package com.monitoramento.eletrodomestico.persistence.repository;

import com.monitoramento.eletrodomestico.persistence.entity.Eletrodomestico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EletrodomesticoRepository extends MongoRepository<Eletrodomestico, String> {
}
