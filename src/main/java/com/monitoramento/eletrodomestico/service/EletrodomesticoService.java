package com.monitoramento.eletrodomestico.service;

import com.monitoramento.eletrodomestico.dto.request.EletrodomesticoRequest;
import com.monitoramento.eletrodomestico.dto.response.EletrodomesticoResponse;
import com.monitoramento.eletrodomestico.persistence.entity.Eletrodomestico;
import com.monitoramento.eletrodomestico.persistence.repository.EletrodomesticoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EletrodomesticoService {

    private EletrodomesticoRepository eletrodomesticoRepository;

    public EletrodomesticoResponse adicionarNovoEletrodomestico(EletrodomesticoRequest eletrodomesticoForm){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(EletrodomesticoRequest.class, Eletrodomestico.class).addMappings(mapper -> mapper.skip(Eletrodomestico::setId));

        Eletrodomestico eletrodomestico = modelMapper.map(eletrodomesticoForm, Eletrodomestico.class);

        eletrodomesticoRepository.save(eletrodomestico);

        return modelMapper.map(eletrodomestico, EletrodomesticoResponse.class);
    }
}
