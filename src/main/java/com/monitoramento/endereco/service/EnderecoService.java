package com.monitoramento.endereco.service;

import com.monitoramento.endereco.dto.request.VinculoPessoaEnderecoRequest;
import com.monitoramento.endereco.dto.request.EnderecoRequest;
import com.monitoramento.endereco.dto.response.EnderecoResponse;
import com.monitoramento.endereco.persistence.entity.Endereco;
import com.monitoramento.endereco.persistence.repository.EnderecoRepository;
import com.monitoramento.exceptions.PessoaVinculadaException;
import com.monitoramento.pessoa.persistence.entity.Pessoa;
import com.monitoramento.pessoa.service.PessoaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.monitoramento.utils.Validacao.campoNaoFoiAlterado;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private PessoaService pessoaService;

    public EnderecoResponse adicionarNovoEndereco(EnderecoRequest enderecoRequest, String idUsuario) {
        Endereco endereco = toEndereco(enderecoRequest);
        endereco.setIdUsuario(idUsuario);

        enderecoRepository.save(endereco);

        return toEnderecoResponse(endereco);
    }

    public Page<EnderecoResponse> filtrarEnderecos(String idUsuario, String rua, String numero, String bairro,
                                                   String cidade, String estado, Pageable pageable) {
        if (rua == null && numero == null && bairro == null && cidade == null && estado == null) {
            return enderecoRepository.findAllByIdUsuario(idUsuario, pageable)
                    .map(this::toEnderecoResponse);
        }

        return enderecoRepository.findEnderecosByIdUsuarioAndRuaOrNumeroOrBairroOrCidadeOrEstado(idUsuario, rua, numero,
                bairro, cidade, estado, pageable).map(this::toEnderecoResponse);
    }

    public EnderecoResponse atualizarEndereco(EnderecoRequest enderecoRequest, String idEndereco, String idUsuario)
            throws ChangeSetPersister.NotFoundException {
        Endereco enderecoRecuperado = enderecoRepository.findEnderecoByIdUsuario(idEndereco, idUsuario)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Endereco endereco = toEndereco(enderecoRequest);

        Endereco enderecoAlterado = new Endereco(
                enderecoRecuperado.getId(),
                enderecoRecuperado.getIdUsuario(),

                campoNaoFoiAlterado(enderecoRecuperado.getRua(), endereco.getRua())
                        ? enderecoRecuperado.getRua()
                        : endereco.getRua(),

                campoNaoFoiAlterado(enderecoRecuperado.getNumero(), endereco.getNumero())
                        ? enderecoRecuperado.getNumero()
                        : endereco.getNumero(),

                campoNaoFoiAlterado(enderecoRecuperado.getBairro(), endereco.getBairro())
                        ? enderecoRecuperado.getBairro()
                        : endereco.getBairro(),

                campoNaoFoiAlterado(enderecoRecuperado.getCidade(), endereco.getCidade())
                        ? enderecoRecuperado.getCidade()
                        : endereco.getCidade(),

                campoNaoFoiAlterado(enderecoRecuperado.getEstado(), endereco.getEstado())
                        ? enderecoRecuperado.getEstado()
                        : endereco.getEstado()

        );

        enderecoRepository.save(enderecoAlterado);

        return toEnderecoResponse(enderecoAlterado);
    }

    public EnderecoResponse vincularPessoaEndereco(VinculoPessoaEnderecoRequest adicionaPessoaRequest, String idEndereco, String idUsuario) throws ChangeSetPersister.NotFoundException, PessoaVinculadaException {
        Endereco endereco = enderecoRepository.findEnderecoByIdUsuario(idEndereco, idUsuario)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Pessoa pessoa = pessoaService.buscaPessoaPorId(adicionaPessoaRequest.getIdPessoa(), idUsuario);

        if (pessoa.getVinculada()) {
            throw new PessoaVinculadaException();
        }

        endereco.getPessoas().add(pessoa.getId());

        enderecoRepository.save(endereco);
        pessoaService.vincularPessoa(idUsuario, pessoa.getId());

        return toEnderecoResponse(endereco);
    }

    private EnderecoResponse toEnderecoResponse(Endereco endereco) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(endereco, EnderecoResponse.class);

        return modelMapper.map(endereco, EnderecoResponse.class);
    }

    private Endereco toEndereco(EnderecoRequest enderecoRequest) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(EnderecoRequest.class, Endereco.class).addMappings(mapper -> mapper.skip(Endereco::setId));

        return modelMapper.map(enderecoRequest, Endereco.class);
    }
}
