package com.hugo.hbs64.ocorrenciaapi.entities.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteDTO(@NotNull String nmeCliente, @NotNull String nroCpf, LocalDate dtaNascimento) {
}
