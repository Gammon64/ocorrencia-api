package com.hugo.hbs64.ocorrenciaapi.entities.mappers;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.ClienteDTO;

public class ClienteMapper {

    public static final ClienteMapper INSTANCE = new ClienteMapper();

    public Cliente fromDto(ClienteDTO clienteDTO) {
        return new Cliente(null, clienteDTO.nmeCliente(), clienteDTO.dtaNascimento(), clienteDTO.nroCpf(), null);
    }
}
