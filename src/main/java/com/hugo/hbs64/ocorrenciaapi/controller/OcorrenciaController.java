package com.hugo.hbs64.ocorrenciaapi.controller;

import com.hugo.hbs64.ocorrenciaapi.entities.Ocorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FiltroOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FormOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.services.OcorrenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @Operation(summary = "Retorna uma lista de ocorrências")
    @GetMapping()
    public Page<Ocorrencia> getOcorrencias(@ParameterObject FiltroOcorrenciaDTO filtroOcorrenciaDTO, @PageableDefault(sort = {"codOcorrencia", "endereco.nmeCidade"}) @ParameterObject Pageable pageable) {
        return ocorrenciaService.findAllByFilter(filtroOcorrenciaDTO, pageable);
    }

    @Operation(summary = "Retorna uma ocorrência")
    @GetMapping("/{cod_ocorrencia}")
    public Ocorrencia getOcorrencia(@Parameter(description = "Código da ocorrência") @PathVariable("cod_ocorrencia") Long codOcorrencia) {
        return ocorrenciaService.findById(codOcorrencia);
    }

    @Operation(summary = "Cria uma ocorrência")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ocorrencia createOcorrencia(@ModelAttribute FormOcorrenciaDTO formOcorrenciaDTO) {
        return ocorrenciaService.create(formOcorrenciaDTO);
    }

    @Operation(summary = "Atualiza uma ocorrência")
    @PatchMapping("/{cod_ocorrencia}")
    public Ocorrencia updateOcorrencia(@Parameter(description = "Código da ocorrência") @PathVariable("cod_ocorrencia") Long codOcorrencia, @RequestBody FormOcorrenciaDTO formOcorrenciaDTO) {
        return ocorrenciaService.update(codOcorrencia, formOcorrenciaDTO);
    }

    @Operation(summary = "Finaliza uma ocorrência")
    @PatchMapping("/finalizar/{cod_ocorrencia}")
    public Ocorrencia updateOcorrencia(@Parameter(description = "Código da ocorrência") @PathVariable("cod_ocorrencia") Long codOcorrencia) {
        return ocorrenciaService.finalizarOcorrencia(codOcorrencia);
    }

    @Operation(summary = "Exclui uma ocorrência")
    @DeleteMapping("/{cod_ocorrencia}")
    public void deleteOcorrencia(@Parameter(description = "Código da ocorrência") @PathVariable("cod_ocorrencia") Long codOcorrencia) {
        ocorrenciaService.delete(codOcorrencia);
    }
}
