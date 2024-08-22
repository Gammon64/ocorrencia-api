package com.hugo.hbs64.ocorrenciaapi.controller;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.EnderecoDTO;
import com.hugo.hbs64.ocorrenciaapi.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Retorna uma lista de endereços")
    @GetMapping()
    public Page<Endereco> getEnderecos(@PageableDefault(sort = {"codEndereco"}) @ParameterObject Pageable pageable) {
        return enderecoService.findAll(pageable);
    }

    @Operation(summary = "Retorna um endereço")
    @GetMapping("/{cod_endereco}")
    public Endereco getEndereco(@Parameter(description = "Código do endereço") @PathVariable("cod_endereco") Long codEndereco) {
        return enderecoService.findById(codEndereco);
    }

    @Operation(summary = "Cria um endereço")
    @PostMapping()
    public Endereco createEndereco(@RequestBody EnderecoDTO endereco) {
        return enderecoService.create(endereco);
    }

    @Operation(summary = "Atualiza um endereço")
    @PatchMapping("/{cod_endereco}")
    public Endereco updateEndereco(@Parameter(description = "Código do endereço") @PathVariable("cod_endereco") Long codEndereco, @RequestBody EnderecoDTO endereco) {
        return enderecoService.update(codEndereco, endereco);
    }

    @Operation(summary = "Exclui um endereço")
    @DeleteMapping("/{cod_endereco}")
    public void deleteEndereco(@Parameter(description = "Código do endereço") @PathVariable("cod_endereco") Long codEndereco) {
        enderecoService.deleteById(codEndereco);
    }
}
