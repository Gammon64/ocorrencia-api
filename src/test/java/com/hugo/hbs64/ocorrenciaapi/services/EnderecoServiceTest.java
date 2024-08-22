package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.repositories.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.hugo.hbs64.ocorrenciaapi.entities.EnderecoConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    void createEndereco_withValidData_ReturnEndereco() {
        when(enderecoRepository.save(any())).thenReturn(ENDERECO);
        Endereco endereco = enderecoService.create(ENDERECO_DTO);
        assertThat(endereco).isEqualTo(ENDERECO);
    }

    @Test
    void createEndereco_withInvalidData_ThrowsException() {
        when(enderecoRepository.save(any())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> enderecoService.create(ENDERECO_INVALIDO_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateEndereco_withValidData_ReturnEndereco() {
        when(enderecoRepository.save(any())).thenReturn(ENDERECO);
        Endereco endereco = enderecoService.update(1L, ENDERECO_DTO);
        assertThat(endereco).isEqualTo(ENDERECO);
    }

    @Test
    void updateEndereco_withInvalidData_ThrowsException() {
        when(enderecoRepository.save(any())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> enderecoService.update(1L, ENDERECO_INVALIDO_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getEndereco_withValidId_ReturnEndereco() {
        when(enderecoRepository.findById(ENDERECO.getCodEndereco())).thenReturn(Optional.of(ENDERECO));
        Endereco endereco = enderecoService.findById(ENDERECO.getCodEndereco());
        assertThat(endereco).isEqualTo(ENDERECO);
    }

    @Test
    void getEndereco_withInvalidId_ThrowsException() {
        when(enderecoRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> enderecoService.findById(1L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void listEnderecos_withPageable_ReturnPage() {
        when(enderecoRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of(ENDERECO)));
        Page<Endereco> enderecoPage = enderecoService.findAll(Pageable.unpaged());
        assertThat(enderecoPage).isNotEmpty().hasSize(1).contains(ENDERECO);
    }

    @Test
    void listEnderecos_withPageable_ReturnEmptyPage() {
        when(enderecoRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of()));
        assertThat(enderecoService.findAll(Pageable.unpaged())).isEmpty();
    }

    @Test
    void deleteEndereco_withValidId_ReturnVoid() {
        assertThatCode(() -> enderecoService.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    void deleteEndereco_withInvalidId_ThrowsException() {
        doThrow(RuntimeException.class).when(enderecoRepository).deleteById(99L);
        assertThatThrownBy(() -> enderecoService.deleteById(99L)).isInstanceOf(RuntimeException.class);
    }
}
