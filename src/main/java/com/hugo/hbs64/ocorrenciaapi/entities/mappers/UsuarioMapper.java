package com.hugo.hbs64.ocorrenciaapi.entities.mappers;

import com.hugo.hbs64.ocorrenciaapi.entities.Usuario;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toEntity(UsuarioDTO usuarioDTO);

}
