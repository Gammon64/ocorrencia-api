package com.hugo.hbs64.ocorrenciaapi.controller;

import com.hugo.hbs64.ocorrenciaapi.entities.dtos.UsuarioDTO;
import com.hugo.hbs64.ocorrenciaapi.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cadastra um usuário")
    @PostMapping("/cadastrar")
    public void createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.create(usuarioDTO);
    }

    @Operation(summary = "Autentica um usuário")
    @PostMapping("/autenticar")
    public String authenticateUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.authenticate(usuarioDTO);
    }

}
