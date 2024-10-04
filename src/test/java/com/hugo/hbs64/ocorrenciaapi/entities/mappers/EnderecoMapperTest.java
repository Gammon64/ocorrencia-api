package com.hugo.hbs64.ocorrenciaapi.entities.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;

class EnderecoMapperTest {
    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setUp() {
        enderecoDTO = new EnderecoDTO(
                "Rua dos Bobos",
                "Jardim das Flores",
                "12345678",
                "São Paulo",
                "SP");
    }

    @Test
    void mapEndereco_withEnderecoDTO_shouldReturnEnderecoDTO() {
        Endereco cliente = EnderecoMapper.INSTANCE.fromDto(enderecoDTO);
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNmeLogradouro()).isEqualTo(enderecoDTO.nmeLogradouro());
        assertThat(cliente.getNmeBairro()).isEqualTo(enderecoDTO.nmeBairro());
        assertThat(cliente.getNroCep()).isEqualTo(enderecoDTO.nroCep());
        assertThat(cliente.getNmeCidade()).isEqualTo(enderecoDTO.nmeCidade());
        assertThat(cliente.getNmeEstado()).isEqualTo(enderecoDTO.nmeEstado());
    }
}
