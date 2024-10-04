package com.hugo.hbs64.ocorrenciaapi.entities.mappers;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;

public class EnderecoMapper {
    public static final EnderecoMapper INSTANCE = new EnderecoMapper();

    public Endereco fromDto(EnderecoDTO enderecoDTO) {
        return new Endereco(null, enderecoDTO.nmeLogradouro(), enderecoDTO.nmeBairro(), enderecoDTO.nroCep(),
                enderecoDTO.nmeCidade(), enderecoDTO.nmeEstado());
    }
}
