package com.hugo.hbs64.ocorrenciaapi.entities.mappers;


import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import org.junit.jupiter.api.Test;

import static com.hugo.hbs64.ocorrenciaapi.entities.EnderecoConstants.ENDERECO_DTO;
import static org.assertj.core.api.Assertions.assertThat;

class EnderecoMapperTest {

    @Test
    void mapEndereco_withEnderecoDTO_shouldReturnEnderecoDTO() {
        Endereco cliente = EnderecoMapper.INSTANCE.fromDto(ENDERECO_DTO);
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNmeLogradouro()).isEqualTo(ENDERECO_DTO.nmeLogradouro());
        assertThat(cliente.getNmeBairro()).isEqualTo(ENDERECO_DTO.nmeBairro());
        assertThat(cliente.getNroCep()).isEqualTo(ENDERECO_DTO.nroCep());
        assertThat(cliente.getNmeCidade()).isEqualTo(ENDERECO_DTO.nmeCidade());
        assertThat(cliente.getNmeEstado()).isEqualTo(ENDERECO_DTO.nmeEstado());
    }
}
