package com.monitoramento.pessoa.service;

import com.monitoramento.pessoa.dto.request.PessoaRequest;
import com.monitoramento.pessoa.dto.response.PessoaResponse;
import com.monitoramento.pessoa.persistence.entity.Pessoa;
import com.monitoramento.pessoa.persistence.repository.PessoaRepository;
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
import static com.monitoramento.utils.Validacao.dataNaoFoiAlterada;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private UsuarioService usuarioService;

    public PessoaResponse adicionarNovaPessoa(PessoaRequest pessoaRequest, String idUsuario) throws ChangeSetPersister.NotFoundException {

        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Pessoa pessoa = toPessoa(pessoaRequest);

        pessoa.setIdUsuario(usuario.getId());

        pessoaRepository.save(pessoa);

        return toPessoaResponse(pessoa);

    }

    public Page<PessoaResponse> filtrarPessoas(String idUsuario, String nome, String sexo, String parentesco, Pageable pageable) throws ChangeSetPersister.NotFoundException {

        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        if (nome == null && sexo == null && parentesco == null) {
            return pessoaRepository.findAllByIdUsuario(usuario.getId(), pageable)
                    .map(this::toPessoaResponse);
        }

        return pessoaRepository.findPessoasByIdUsuarioAndNomeOrParentescoOrSexo(usuario.getId(), nome, parentesco,
                sexo, pageable).map(this::toPessoaResponse);

    }

    public PessoaResponse atualizarPessoa(PessoaRequest pessoaRequest, String idPessoa, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);
        Pessoa pessoaRecuperada = pessoaRepository.findPessoaByIdAndIdUsuario(idPessoa, usuario.getId())
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

    public void deletarPessoa(String idPessoa, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Pessoa pessoa = pessoaRepository.findPessoaByIdAndIdUsuario(idPessoa, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        pessoaRepository.delete(pessoa);
    }

    public void vincularPessoa(String idUsuario, String idPessoa) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Pessoa pessoa = pessoaRepository.findPessoaByIdAndIdUsuario(idPessoa, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        pessoa.setVinculada(true);

        pessoaRepository.save(pessoa);

    }

    public Pessoa buscaPessoaPorId(String idPessoa, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        return pessoaRepository.findPessoaByIdAndIdUsuario(idPessoa, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
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
}