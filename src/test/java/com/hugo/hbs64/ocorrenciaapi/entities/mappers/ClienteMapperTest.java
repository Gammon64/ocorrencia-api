package com.hugo.hbs64.ocorrenciaapi.entities.mappers;


import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import org.junit.jupiter.api.Test;

import static com.hugo.hbs64.ocorrenciaapi.entities.ClienteConstants.CLIENTE_DTO;
import static org.assertj.core.api.Assertions.assertThat;

class ClienteMapperTest {

    @Test
    void mapCliente_withClienteDTO_shouldReturnClienteDTO() {
        Cliente cliente = ClienteMapper.INSTANCE.fromDto(CLIENTE_DTO);
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNmeCliente()).isEqualTo(CLIENTE_DTO.nmeCliente());
        assertThat(cliente.getDtaNascimento()).isEqualTo(CLIENTE_DTO.dtaNascimento());
        assertThat(cliente.getNroCpf()).isEqualTo(CLIENTE_DTO.nroCpf());
    }
}
