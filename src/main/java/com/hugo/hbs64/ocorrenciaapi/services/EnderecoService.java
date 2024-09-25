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

    /**
     * Busca todos os endereços.
     * @param pageable Paginação.
     * @return Página de endereços.
     */
    public Page<Endereco> findAll(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    /**
     * Busca um endereço pelo ID.
     * @param id ID do endereço.
     * @return Endereço.
     */
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow();
    }

    /**
     * Busca um endereço pelos Dados da ocorrência.
     * @param formOcorrenciaDTO Dados da ocorrência.
     * @return Endereço encontrado.
     * @throws Exception Endereço não encontrado.
     */
    public Endereco findByOcorrenciaDTO(FormOcorrenciaDTO formOcorrenciaDTO) {
        return enderecoRepository.findByNmeLogradouroAndNmeBairroAndNroCep(formOcorrenciaDTO.nmeLogradouro(), formOcorrenciaDTO.nmeBairro(), formOcorrenciaDTO.nroCep()).orElseThrow();
    }

    /**
     * Cria um novo endereço.
     * @param enderecoDTO Dados do endereço.
     * @return Endereço criado.
     */
    public Endereco create(EnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.INSTANCE.fromDto(enderecoDTO);
        return enderecoRepository.save(endereco);
    }

    /**
     * Atualiza um endereço.
     * @param codEndereco ID do endereço.
     * @param enderecoDTO Dados do endereço.
     * @return Endereço atualizado.
     */
    public Endereco update(Long codEndereco, EnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.INSTANCE.fromDto(enderecoDTO);
        endereco.setCodEndereco(codEndereco);
        return enderecoRepository.save(endereco);
    }

    /**
     * Deleta um endereço pelo ID.
     * @param id ID do endereço.
     */
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
