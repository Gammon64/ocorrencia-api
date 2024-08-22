package com.hugo.hbs64.ocorrenciaapi.entities.dtos;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record FiltroOcorrenciaDTO(String nmeCliente,
                                  @Size(min = 11, max = 11, message = "Insira apenas os números. Ex: 22222222222") String nroCpf,
                                  LocalDate dtaOcorrencia, String nmeCidade) {
}
