package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.repositories.ClienteRepository;
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

import static com.hugo.hbs64.ocorrenciaapi.entities.ClienteConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void createCliente_withValidData_ReturnCliente() {
        when(clienteRepository.save(any())).thenReturn(CLIENTE);
        Cliente cliente = clienteService.create(CLIENTE_DTO);
        assertThat(cliente).isEqualTo(CLIENTE);
    }

    @Test
    void createCliente_withInvalidData_ThrowsException() {
        when(clienteRepository.save(any())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> clienteService.create(CLIENTE_INVALIDO_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateCliente_withValidData_ReturnCliente() {
        when(clienteRepository.save(any())).thenReturn(CLIENTE);
        Cliente cliente = clienteService.update(1L, CLIENTE_DTO);
        assertThat(cliente).isEqualTo(CLIENTE);
    }

    @Test
    void updateCliente_withInvalidData_ThrowsException() {
        when(clienteRepository.save(any())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> clienteService.update(1L, CLIENTE_INVALIDO_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getCliente_withValidId_ReturnCliente() {
        when(clienteRepository.findById(CLIENTE.getCodCliente())).thenReturn(Optional.of(CLIENTE));
        Cliente cliente = clienteService.findById(CLIENTE.getCodCliente());
        assertThat(cliente).isEqualTo(CLIENTE);
    }

    @Test
    void getCliente_withInvalidId_ThrowsException() {
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> clienteService.findById(1L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void listClientes_withPageable_ReturnPage() {
        when(clienteRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of(CLIENTE)));
        Page<Cliente> clientePage = clienteService.findAll(Pageable.unpaged());
        assertThat(clientePage).isNotEmpty().hasSize(1).contains(CLIENTE);
    }

    @Test
    void listClientes_withPageable_ReturnEmptyPage() {
        when(clienteRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of()));
        assertThat(clienteService.findAll(Pageable.unpaged())).isEmpty();
    }

    @Test
    void deleteCliente_withValidId_ReturnVoid() {
        assertThatCode(() -> clienteService.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    void deleteCliente_withInvalidId_ThrowsException() {
        doThrow(RuntimeException.class).when(clienteRepository).deleteById(99L);
        assertThatThrownBy(() -> clienteService.deleteById(99L)).isInstanceOf(RuntimeException.class);
    }
}
