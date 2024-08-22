package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FormOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.mappers.EnderecoMapper;
import com.hugo.hbs64.ocorrenciaapi.repositories.EnderecoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Page<Endereco> findAll(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow();
    }

    public Endereco findByOcorrenciaDTO(FormOcorrenciaDTO formOcorrenciaDTO) {
        return enderecoRepository.findByNmeLogradouroAndNmeBairroAndNroCep(formOcorrenciaDTO.nmeLogradouro(), formOcorrenciaDTO.nmeBairro(), formOcorrenciaDTO.nroCep()).orElseThrow();
    }

    public Endereco create(EnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.INSTANCE.fromDto(enderecoDTO);
        return enderecoRepository.save(endereco);
    }

    public Endereco update(Long codEndereco, EnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.INSTANCE.fromDto(enderecoDTO);
        endereco.setCodEndereco(codEndereco);
        return enderecoRepository.save(endereco);
    }

    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
