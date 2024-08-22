package com.hugo.hbs64.ocorrenciaapi.entities;

import com.hugo.hbs64.ocorrenciaapi.entities.dtos.ClienteDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteConstants {
    public static final ClienteDTO CLIENTE_DTO = new ClienteDTO("Cliente 1", "22222222222", LocalDate.of(2000, 4, 18));
    public static final ClienteDTO CLIENTE_INVALIDO_DTO = new ClienteDTO(null, null, null);

    public static final Cliente CLIENTE = new Cliente(1L, CLIENTE_DTO.nmeCliente(), CLIENTE_DTO.dtaNascimento(), CLIENTE_DTO.nroCpf(), LocalDateTime.now());
}
