package com.monitoramento.eletrodomestico.service;

import com.monitoramento.eletrodomestico.dto.request.EletrodomesticoRequest;
import com.monitoramento.eletrodomestico.dto.response.EletrodomesticoResponse;
import com.monitoramento.eletrodomestico.persistence.entity.Eletrodomestico;
import com.monitoramento.eletrodomestico.persistence.repository.EletrodomesticoRepository;
import com.monitoramento.usuario.persistence.entity.Usuario;
import com.monitoramento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.monitoramento.utils.Validacao.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EletrodomesticoService {

    private EletrodomesticoRepository eletrodomesticoRepository;
    private UsuarioService usuarioService;

    public EletrodomesticoResponse adicionarNovoEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Eletrodomestico eletrodomestico = toEletrodomestico(eletrodomesticoRequest);

        eletrodomestico.setIdUsuario(usuario.getId());

        eletrodomesticoRepository.save(eletrodomestico);

        return toEletrodomesticoResponse(eletrodomestico);
    }

    public Page<EletrodomesticoResponse> filtrarEletrodomesticos(String idUsuario, String nome, String modelo,
                                                                 String potencia, Pageable pageable) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        if (nome == null && modelo == null && potencia == null) {
            return eletrodomesticoRepository.findAllByIdUsuario(usuario.getId(), pageable)
                    .map(this::toEletrodomesticoResponse);
        }

        return eletrodomesticoRepository.findEletrodomesticosByIdUsuarioAndNomeOrModeloOrPotencia(usuario.getId(), nome, modelo,
                potencia, pageable).map(this::toEletrodomesticoResponse);
    }

    public void removerEletrodomestico(String idEletrodomestico, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Eletrodomestico eletrodomestico = eletrodomesticoRepository.findEletrodomesticoByIdAndIdUsuario(idEletrodomestico, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        eletrodomesticoRepository.delete(eletrodomestico);
    }

    public EletrodomesticoResponse atualizarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest, String idEletrodomestico, String idUsuario) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Eletrodomestico eletrodomesticoRecuperado = eletrodomesticoRepository.findEletrodomesticoByIdAndIdUsuario(idEletrodomestico, usuario.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Eletrodomestico eletrodomestico = toEletrodomestico(eletrodomesticoRequest);

        Eletrodomestico eletrodomesticoAlterado = new Eletrodomestico(
                eletrodomesticoRecuperado.getId(),
                eletrodomesticoRecuperado.getIdUsuario(),

                campoNaoFoiAlterado(eletrodomesticoRecuperado.getNome(), eletrodomestico.getNome())
                        ? eletrodomesticoRecuperado.getNome()
                        : eletrodomestico.getNome(),

                campoNaoFoiAlterado(eletrodomesticoRecuperado.getModelo(), eletrodomestico.getModelo())
                        ? eletrodomesticoRecuperado.getModelo()
                        : eletrodomestico.getModelo(),

                valorNaoFoiAlterada(eletrodomesticoRecuperado.getPotencia(), eletrodomestico.getPotencia())
                        ? eletrodomesticoRecuperado.getPotencia()
                        : eletrodomestico.getPotencia()
        );

        eletrodomesticoRepository.save(eletrodomesticoAlterado);

        return toEletrodomesticoResponse(eletrodomesticoAlterado);
    }

    private Eletrodomestico toEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(EletrodomesticoRequest.class, Eletrodomestico.class)
                .addMappings(mapper -> mapper.skip(Eletrodomestico::setId));

        return modelMapper.map(eletrodomesticoRequest, Eletrodomestico.class);
    }

    private EletrodomesticoResponse toEletrodomesticoResponse(Eletrodomestico eletrodomestico) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(eletrodomestico, EletrodomesticoResponse.class);
    }
}
