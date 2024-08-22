package com.hugo.hbs64.ocorrenciaapi.entities.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(@NotNull String login, @NotNull @Size(min = 8, max = 100) String senha) {
}
