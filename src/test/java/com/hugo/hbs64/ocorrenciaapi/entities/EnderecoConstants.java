package com.hugo.hbs64.ocorrenciaapi.entities;

import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;

public class EnderecoConstants {
    public static final EnderecoDTO ENDERECO_DTO = new EnderecoDTO("Logradouro 1", "Bairro Teste", "01000000", "Cidade Teste", "ET");
    public static final EnderecoDTO ENDERECO_INVALIDO_DTO = new EnderecoDTO(null, null, null, null, null);

    public static final Endereco ENDERECO = new Endereco(1L, ENDERECO_DTO.nmeLogradouro(), ENDERECO_DTO.nmeBairro(), ENDERECO_DTO.nroCep(), ENDERECO_DTO.nmeCidade(), ENDERECO_DTO.nmeEstado());
}
