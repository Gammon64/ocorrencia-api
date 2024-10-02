package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.ClienteDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FormOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.exceptions.CpfDuplicadoException;
import com.hugo.hbs64.ocorrenciaapi.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Cliente> page = new PageImpl<>(Collections.singletonList(new Cliente()));
        when(clienteRepository.findAll(pageable)).thenReturn(page);

        Page<Cliente> result = clienteService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(clienteRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.findById(id);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void testFindByOcorrenciaDTO() {
        FormOcorrenciaDTO formOcorrenciaDTO = new FormOcorrenciaDTO(
                "Nome", "12345678900", "Logradouro", "Bairro", "01111000", "Cidade", "SP", Collections.emptyList());
        Cliente cliente = new Cliente();
        when(clienteRepository.findByNmeClienteAndNroCpf(formOcorrenciaDTO.nmeCliente(), formOcorrenciaDTO.nroCpf()))
                .thenReturn(Optional.of(cliente));

        Cliente result = clienteService.findByOcorrenciaDTO(formOcorrenciaDTO);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).findByNmeClienteAndNroCpf(formOcorrenciaDTO.nmeCliente(),
                formOcorrenciaDTO.nroCpf());
    }

    @Test
    void testCreate() {
        ClienteDTO clienteDTO = new ClienteDTO("Nome", "12345678900", null);
        Cliente cliente = new Cliente();
        when(clienteRepository.existsByNroCpf(clienteDTO.nroCpf())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.create(clienteDTO);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).existsByNroCpf(clienteDTO.nroCpf());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testCreateWithDuplicateCpf() {
        ClienteDTO clienteDTO = new ClienteDTO("Nome", "12345678900", null);
        when(clienteRepository.existsByNroCpf(clienteDTO.nroCpf())).thenReturn(true);

        assertThrows(CpfDuplicadoException.class, () -> clienteService.create(clienteDTO));
        verify(clienteRepository, times(1)).existsByNroCpf(clienteDTO.nroCpf());
        verify(clienteRepository, times(0)).save(any(Cliente.class));
    }

    @Test
    void testUpdate() {
        Long codCliente = 1L;
        ClienteDTO clienteDTO = new ClienteDTO("Nome", "12345678900", null);
        Cliente cliente = new Cliente();
        when(clienteRepository.existsByNroCpf(clienteDTO.nroCpf())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.update(codCliente, clienteDTO);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).existsByNroCpf(clienteDTO.nroCpf());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdateWithDuplicateCpf() {
        Long codCliente = 1L;
        ClienteDTO clienteDTO = new ClienteDTO("Nome", "12345678900", null);
        when(clienteRepository.existsByNroCpf(clienteDTO.nroCpf())).thenReturn(true);

        assertThrows(CpfDuplicadoException.class, () -> clienteService.update(codCliente, clienteDTO));
        verify(clienteRepository, times(1)).existsByNroCpf(clienteDTO.nroCpf());
        verify(clienteRepository, times(0)).save(any(Cliente.class));
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        clienteService.deleteById(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }
}