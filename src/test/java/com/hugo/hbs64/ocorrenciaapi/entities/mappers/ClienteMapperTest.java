package com.hugo.hbs64.ocorrenciaapi.entities.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.ClienteDTO;

class ClienteMapperTest {
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO(
                "John Doe",
                "12345678901",
                LocalDate.of(2000, 04, 18));
    }

    @Test
    void mapCliente_withClienteDTO_shouldReturnClienteDTO() {
        Cliente cliente = ClienteMapper.INSTANCE.fromDto(clienteDTO);
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNmeCliente()).isEqualTo(clienteDTO.nmeCliente());
        assertThat(cliente.getDtaNascimento()).isEqualTo(clienteDTO.dtaNascimento());
        assertThat(cliente.getNroCpf()).isEqualTo(clienteDTO.nroCpf());
    }
}
