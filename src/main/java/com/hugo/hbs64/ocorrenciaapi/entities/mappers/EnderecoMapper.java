package com.hugo.hbs64.ocorrenciaapi.entities.mappers;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco fromDto(EnderecoDTO enderecoDTO);
}
