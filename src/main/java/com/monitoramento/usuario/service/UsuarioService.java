package com.monitoramento.usuario.service;

import com.monitoramento.exceptions.UsuarioJaExisteException;
import com.monitoramento.usuario.dto.request.UsuarioRequest;
import com.monitoramento.usuario.dto.response.UsuarioResponse;
import com.monitoramento.usuario.persistence.entity.Usuario;
import com.monitoramento.usuario.persistence.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioResponse adicionarNovaPessoa(UsuarioRequest usuarioRequest) throws UsuarioJaExisteException {

        if (usuarioRepository.findByUsuario(usuarioRequest.getUsuario()).isPresent()){
            throw new UsuarioJaExisteException();
        }

        Usuario usuario = toUsuario(usuarioRequest);

        usuarioRepository.save(usuario);

        return toUsuarioResponse(usuario);

    }
    public void deletarUsuario(String id) throws ChangeSetPersister.NotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        usuarioRepository.delete(usuario);
    }

    public Usuario buscarUsuario(String id) throws ChangeSetPersister.NotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    private Usuario toUsuario(UsuarioRequest usuarioRequest) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(UsuarioRequest.class, Usuario.class).addMappings(mapper -> mapper.skip(Usuario::setId));

        return modelMapper.map(usuarioRequest, Usuario.class);
    }

    private UsuarioResponse toUsuarioResponse(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(usuario, UsuarioResponse.class);

        return modelMapper.map(usuario, UsuarioResponse.class);
    }
}