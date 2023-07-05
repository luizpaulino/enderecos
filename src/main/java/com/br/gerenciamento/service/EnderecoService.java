package com.br.gerenciamento.service;

import com.br.gerenciamento.dominio.Endereco;
import com.br.gerenciamento.form.EnderecoForm;
import com.br.gerenciamento.repository.EnderecoRepository;
import com.br.gerenciamento.view.EnderecoView;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoView adicionarNovoEndereco(EnderecoForm enderecoForm) {

        ModelMapper modelMapper = new ModelMapper();

        Endereco endereco = modelMapper.map(enderecoForm, Endereco.class);

        enderecoRepository.salvar(endereco);

        return modelMapper.map(endereco, EnderecoView.class);

    }
}
