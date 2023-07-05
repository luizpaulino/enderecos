package com.br.gerenciamento.service;

import com.br.gerenciamento.dominio.Eletrodomestico;
import com.br.gerenciamento.form.EletrodomesticoForm;
import com.br.gerenciamento.repository.EletrodomesticoRepository;
import com.br.gerenciamento.view.EletrodomesticoView;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EletrodomesticoService {

    private EletrodomesticoRepository eletrodomesticoRepository;

    public EletrodomesticoView adicionarNovoEletrodomestico(EletrodomesticoForm eletrodomesticoForm){
        ModelMapper modelMapper = new ModelMapper();

        Eletrodomestico eletrodomestico = modelMapper.map(eletrodomesticoForm, Eletrodomestico.class);

        eletrodomesticoRepository.salvar(eletrodomestico);

        return modelMapper.map(eletrodomestico, EletrodomesticoView.class);
    }
}
