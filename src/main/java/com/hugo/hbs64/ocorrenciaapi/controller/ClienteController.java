package com.hugo.hbs64.ocorrenciaapi.controller;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.ClienteDTO;
import com.hugo.hbs64.ocorrenciaapi.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Retorna uma lista de clientes")
    @GetMapping()
    public Page<Cliente> getClientes(@PageableDefault(sort = {"nmeCliente"}) @ParameterObject Pageable pageable) {
        return clienteService.findAll(pageable);
    }

    @Operation(summary = "Retorna um cliente")
    @GetMapping("/{cod_cliente}")
    public Cliente getCliente(@Parameter(description = "Código do cliente") @PathVariable("cod_cliente") Long codCliente) {
        return clienteService.findById(codCliente);
    }

    @Operation(summary = "Cria um cliente")
    @PostMapping()
    public Cliente createCliente(@RequestBody ClienteDTO cliente) {
        return clienteService.create(cliente);
    }

    @Operation(summary = "Atualiza um cliente")
    @PatchMapping("/{cod_cliente}")
    public Cliente updateCliente(@Parameter(description = "Código do cliente") @PathVariable("cod_cliente") Long codCliente, @RequestBody ClienteDTO cliente) {
        return clienteService.update(codCliente, cliente);
    }

    @Operation(summary = "Exclui um cliente")
    @DeleteMapping("/{cod_cliente}")
    public void deleteCliente(@Parameter(description = "Código do cliente") @PathVariable("cod_cliente") Long codCliente) {
        clienteService.deleteById(codCliente);
    }
}
