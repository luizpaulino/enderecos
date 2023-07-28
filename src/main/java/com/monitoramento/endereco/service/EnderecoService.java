package com.monitoramento.endereco.service;

import com.monitoramento.endereco.dto.request.EnderecoRequest;
import com.monitoramento.endereco.dto.response.EnderecoResponse;
import com.monitoramento.endereco.persistence.entity.Endereco;
import com.monitoramento.endereco.persistence.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoResponse adicionarNovoEndereco(EnderecoRequest enderecoRequest) {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(EnderecoRequest.class, Endereco.class).addMappings(mapper -> mapper.skip(Endereco::setId));

        Endereco endereco = modelMapper.map(enderecoRequest, Endereco.class);

        enderecoRepository.save(endereco);

        return modelMapper.map(endereco, EnderecoResponse.class);

    }
}
