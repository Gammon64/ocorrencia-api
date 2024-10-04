package com.hugo.hbs64.ocorrenciaapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FormOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.repositories.EnderecoRepository;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Endereco> enderecoPage = new PageImpl<>(Collections.singletonList(new Endereco()));
        when(enderecoRepository.findAll(pageable)).thenReturn(enderecoPage);

        Page<Endereco> result = enderecoService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(enderecoRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));

        Endereco result = enderecoService.findById(id);

        assertNotNull(result);
        assertEquals(endereco, result);
        verify(enderecoRepository, times(1)).findById(id);
    }

    @Test
    void testFindByOcorrenciaDTO() {
        FormOcorrenciaDTO formOcorrenciaDTO = new FormOcorrenciaDTO(
                "Nome", "12345678900", "Logradouro", "Bairro", "01111000", "Cidade", "SP", Collections.emptyList());
        Endereco endereco = new Endereco();
        when(enderecoRepository.findByNmeLogradouroAndNmeBairroAndNroCep(formOcorrenciaDTO.nmeLogradouro(),
                formOcorrenciaDTO.nmeBairro(), formOcorrenciaDTO.nroCep())).thenReturn(Optional.of(endereco));

        Endereco result = enderecoService.findByOcorrenciaDTO(formOcorrenciaDTO);

        assertNotNull(result);
        assertEquals(endereco, result);
        verify(enderecoRepository, times(1)).findByNmeLogradouroAndNmeBairroAndNroCep(formOcorrenciaDTO.nmeLogradouro(),
                formOcorrenciaDTO.nmeBairro(), formOcorrenciaDTO.nroCep());
    }

    @Test
    void testCreate() {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                "Rua A",
                "Bairro B",
                "12345678",
                "Cidade C",
                "SP");
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco result = enderecoService.create(enderecoDTO);

        assertNotNull(result);
        assertEquals(endereco, result);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testUpdate() {
        Long codEndereco = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                "Rua A",
                "Bairro B",
                "12345678",
                "Cidade C",
                "SP");
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco result = enderecoService.update(codEndereco, enderecoDTO);

        assertNotNull(result);
        assertEquals(endereco, result);
        assertEquals(codEndereco, result.getCodEndereco());
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(enderecoRepository).deleteById(id);

        enderecoService.deleteById(id);

        verify(enderecoRepository, times(1)).deleteById(id);
    }
}