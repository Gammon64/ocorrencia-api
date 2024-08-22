package com.hugo.hbs64.ocorrenciaapi.entities.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(@NotNull String nmeLogradouro, @NotNull String nmeBairro,
                          @NotNull @Size(min = 8, max = 8, message = "Insira apenas os números do CEP. Ex: 01111000") String nroCep,
                          @NotNull String nmeCidade,
                          @NotNull @Size(min = 2, max = 2, message = "Insira a sigla do estado. Ex: SP") String nmeEstado) {
}
