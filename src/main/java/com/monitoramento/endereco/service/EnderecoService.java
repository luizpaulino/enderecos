package com.monitoramento.endereco.service;

import com.monitoramento.endereco.dto.request.VinculoPessoaEnderecoRequest;
import com.monitoramento.endereco.dto.request.EnderecoRequest;
import com.monitoramento.endereco.dto.response.EnderecoResponse;
import com.monitoramento.endereco.persistence.entity.Endereco;
import com.monitoramento.endereco.persistence.repository.EnderecoRepository;
import com.monitoramento.exceptions.PessoaVinculadaException;
import com.monitoramento.pessoa.persistence.entity.Pessoa;
import com.monitoramento.pessoa.service.PessoaService;
import com.monitoramento.usuario.persistence.entity.Usuario;
import com.monitoramento.usuario.service.UsuarioService;
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
    private UsuarioService usuarioService;

    public EnderecoResponse adicionarNovoEndereco(EnderecoRequest enderecoRequest, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);
        Endereco endereco = toEndereco(enderecoRequest);
        endereco.setIdUsuario(usuario.getId());

        enderecoRepository.save(endereco);

        return toEnderecoResponse(endereco);
    }

    public Page<EnderecoResponse> filtrarEnderecos(String idUsuario, String rua, String numero, String bairro,
                                                   String cidade, String estado, Pageable pageable) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        if (rua == null && numero == null && bairro == null && cidade == null && estado == null) {
            return enderecoRepository.findAllByIdUsuario(usuario.getId(), pageable)
                    .map(this::toEnderecoResponse);
        }

        return enderecoRepository.findEnderecosByIdUsuarioAndRuaOrNumeroOrBairroOrCidadeOrEstado(usuario.getId(), rua, numero,
                bairro, cidade, estado, pageable).map(this::toEnderecoResponse);
    }

    public EnderecoResponse atualizarEndereco(EnderecoRequest enderecoRequest, String idEndereco, String idUsuario)
            throws ChangeSetPersister.NotFoundException {

        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Endereco enderecoRecuperado = enderecoRepository.findEnderecoByIdUsuario(idEndereco, usuario.getId())
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
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Endereco endereco = enderecoRepository.findEnderecoByIdUsuario(idEndereco, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Pessoa pessoa = pessoaService.buscaPessoaPorId(adicionaPessoaRequest.getIdPessoa(), usuario.getId());

        if (pessoa.getVinculada()) {
            throw new PessoaVinculadaException();
        }

        endereco.getPessoas().add(pessoa.getId());

        enderecoRepository.save(endereco);
        pessoaService.vincularPessoa(usuario.getId(), pessoa.getId());

        return toEnderecoResponse(endereco);
    }

    public void removerEndereco(String idUsuario, String idEndereco) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Endereco endereco = enderecoRepository.findEnderecoByIdUsuario(idEndereco, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        enderecoRepository.delete(endereco);
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
