package com.monitoramento.pessoa.service;

import com.monitoramento.pessoa.dto.request.PessoaRequest;
import com.monitoramento.pessoa.persistence.repository.PessoaRepository;
import com.monitoramento.pessoa.dto.response.PessoaResponse;
import com.monitoramento.pessoa.persistence.entity.Pessoa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaResponse adicionarNovaPessoa(PessoaRequest pessoaRequest) {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(PessoaRequest.class, Pessoa.class).addMappings(mapper -> mapper.skip(Pessoa::setId));

        Pessoa pessoa = modelMapper.map(pessoaRequest, Pessoa.class);

        pessoaRepository.save(pessoa);

        return modelMapper.map(pessoa, PessoaResponse.class);

    }

    public Page<PessoaResponse> filtrarPessoas(String idUsuario, String nome, String sexo, String parentesco, Pageable pageable) {

        ModelMapper modelMapper = new ModelMapper();

        if (nome == null && sexo == null && parentesco == null) {
            return pessoaRepository.findAllByIdUsuario(idUsuario, pageable)
                    .map(pessoa -> modelMapper.map(pessoa, PessoaResponse.class));
        }

        return pessoaRepository.findPessoasByIdUsuarioAndNomeOrParentescoOrSexo(idUsuario, nome, parentesco,
                sexo, pageable).map(pessoa -> modelMapper.map(pessoa, PessoaResponse.class));

    }

}