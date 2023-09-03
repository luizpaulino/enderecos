package com.monitoramento.pessoa.service;

import com.monitoramento.pessoa.dto.request.PessoaRequest;
import com.monitoramento.pessoa.persistence.repository.PessoaRepository;
import com.monitoramento.pessoa.dto.response.PessoaResponse;
import com.monitoramento.pessoa.persistence.entity.Pessoa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaResponse adicionarNovaPessoa(PessoaRequest pessoaRequest, String idUsuario) {

        Pessoa pessoa = toPessoa(pessoaRequest);

        pessoa.setIdUsuario(idUsuario);

        pessoaRepository.save(pessoa);

        return toPessoaResponse(pessoa);

    }

    public Page<PessoaResponse> filtrarPessoas(String idUsuario, String nome, String sexo, String parentesco, Pageable pageable) {

        if (nome == null && sexo == null && parentesco == null) {
            return pessoaRepository.findAllByIdUsuario(idUsuario, pageable)
                    .map(this::toPessoaResponse);
        }

        return pessoaRepository.findPessoasByIdUsuarioAndNomeOrParentescoOrSexo(idUsuario, nome, parentesco,
                sexo, pageable).map(this::toPessoaResponse);

    }

    public PessoaResponse atualizarPessoa(PessoaRequest pessoaRequest, String idPessoa, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Pessoa pessoaRecuperada = pessoaRepository.findPessoaByIdAndIdUsuario(idPessoa, idUsuario)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Pessoa pessoa = toPessoa(pessoaRequest);

        Pessoa pessoaAlterada = new Pessoa(
                pessoaRecuperada.getId(),
                pessoaRecuperada.getIdUsuario(),

                campoNaoFoiAlterado(pessoaRecuperada.getNome(), pessoa.getNome())
                        ? pessoaRecuperada.getNome()
                        : pessoa.getNome(),

                dataNaoFoiAlterada(pessoaRecuperada.getDataNascimento(), pessoa.getDataNascimento())
                        ? pessoaRecuperada.getDataNascimento()
                        : pessoa.getDataNascimento(),

                campoNaoFoiAlterado(pessoaRecuperada.getSexo(), pessoa.getSexo())
                        ? pessoaRecuperada.getSexo()
                        : pessoa.getSexo(),

                campoNaoFoiAlterado(pessoaRecuperada.getParentesco(), pessoa.getParentesco())
                        ? pessoaRecuperada.getParentesco()
                        : pessoa.getParentesco()
        );

        pessoaRepository.save(pessoaAlterada);

        return toPessoaResponse(pessoaAlterada);

    }

    private Pessoa toPessoa(PessoaRequest pessoaRequest) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(PessoaRequest.class, Pessoa.class).addMappings(mapper -> mapper.skip(Pessoa::setId));

        return modelMapper.map(pessoaRequest, Pessoa.class);
    }

    private PessoaResponse toPessoaResponse(Pessoa pessoa) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(pessoa, PessoaResponse.class);

        return modelMapper.map(pessoa, PessoaResponse.class);
    }

    private Boolean campoNaoFoiAlterado(String valorAtual, String valorNovo) {
        return valorAtual.equals(valorNovo) || valorNovo == null;
    }

    private Boolean dataNaoFoiAlterada(LocalDate dataAtual, LocalDate novaData) {
        return dataAtual.equals(novaData) || novaData == null;
    }
}