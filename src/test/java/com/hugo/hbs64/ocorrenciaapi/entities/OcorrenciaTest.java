package com.hugo.hbs64.ocorrenciaapi.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hugo.hbs64.ocorrenciaapi.entities.enums.StatusOcorrencia;

public class OcorrenciaTest {

    @Test
    public void testOcorrenciaConstructorAndGetters() {
        Long codOcorrencia = 1L;
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        LocalDateTime dtaOcorrencia = LocalDateTime.now();
        StatusOcorrencia staOcorrencia = StatusOcorrencia.ATIVA;
        List<FotoOcorrencia> fotosOcorrencia = new ArrayList<>();

        Ocorrencia ocorrencia = new Ocorrencia(
                codOcorrencia,
                cliente,
                endereco,
                dtaOcorrencia,
                staOcorrencia,
                fotosOcorrencia);

        assertEquals(codOcorrencia, ocorrencia.getCodOcorrencia());
        assertEquals(cliente, ocorrencia.getCliente());
        assertEquals(endereco, ocorrencia.getEndereco());
        assertEquals(dtaOcorrencia, ocorrencia.getDtaOcorrencia());
        assertEquals(staOcorrencia, ocorrencia.getStaOcorrencia());
        assertEquals(fotosOcorrencia, ocorrencia.getFotosOcorrencia());
    }

    @Test
    public void testOcorrenciaSetters() {
        Ocorrencia ocorrencia = new Ocorrencia();

        Long codOcorrencia = 1L;
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        LocalDateTime dtaOcorrencia = LocalDateTime.now();
        StatusOcorrencia staOcorrencia = StatusOcorrencia.ATIVA;
        List<FotoOcorrencia> fotosOcorrencia = new ArrayList<>();

        ocorrencia.setCodOcorrencia(codOcorrencia);
        ocorrencia.setCliente(cliente);
        ocorrencia.setEndereco(endereco);
        ocorrencia.setDtaOcorrencia(dtaOcorrencia);
        ocorrencia.setStaOcorrencia(staOcorrencia);
        ocorrencia.setFotosOcorrencia(fotosOcorrencia);

        assertEquals(codOcorrencia, ocorrencia.getCodOcorrencia());
        assertEquals(cliente, ocorrencia.getCliente());
        assertEquals(endereco, ocorrencia.getEndereco());
        assertEquals(dtaOcorrencia, ocorrencia.getDtaOcorrencia());
        assertEquals(staOcorrencia, ocorrencia.getStaOcorrencia());
        assertEquals(fotosOcorrencia, ocorrencia.getFotosOcorrencia());
    }

    @Test
    public void testOcorrenciaNoArgsConstructor() {
        Ocorrencia ocorrencia = new Ocorrencia();

        assertEquals(null, ocorrencia.getCodOcorrencia());
        assertEquals(null, ocorrencia.getCliente());
        assertEquals(null, ocorrencia.getEndereco());
        assertNotNull(ocorrencia.getDtaOcorrencia());
        assertNotNull(ocorrencia.getStaOcorrencia());
        assertNotNull(ocorrencia.getFotosOcorrencia());
    }
}
