package com.br.gerenciamento.service;

import com.br.gerenciamento.dominio.Pessoa;
import com.br.gerenciamento.form.PessoaForm;
import com.br.gerenciamento.repository.PessoaRepository;
import com.br.gerenciamento.view.PessoaView;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;
    public PessoaView adicionarNovaPessoa(PessoaForm pessoaForm) {

        ModelMapper modelMapper = new ModelMapper();

        Pessoa pessoa = modelMapper.map(pessoaForm, Pessoa.class);

        pessoaRepository.salvar(pessoa);

        return modelMapper.map(pessoa, PessoaView.class);

    }
}
