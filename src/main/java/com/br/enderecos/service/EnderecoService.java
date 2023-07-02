package com.br.enderecos.service;

import com.br.enderecos.dominio.Endereco;
import com.br.enderecos.form.EnderecoForm;
import com.br.enderecos.repository.EnderecoRepository;
import com.br.enderecos.view.EnderecoView;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoService {

    EnderecoRepository enderecoRepository;

    public EnderecoView adicionarNovoEndereco(EnderecoForm enderecoForm) {

        ModelMapper modelMapper = new ModelMapper();

        Endereco endereco = modelMapper.map(enderecoForm, Endereco.class);

        enderecoRepository.salvar(endereco);

        return modelMapper.map(endereco, EnderecoView.class);

    }
}
