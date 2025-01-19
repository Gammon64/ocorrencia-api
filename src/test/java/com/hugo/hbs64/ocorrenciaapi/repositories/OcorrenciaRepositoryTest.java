package com.hugo.hbs64.ocorrenciaapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.Ocorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.enums.StatusOcorrencia;

@DataJpaTest
@ActiveProfiles("test")
public class OcorrenciaRepositoryTest {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Cliente cliente;

    private Endereco endereco;

    private Ocorrencia ocorrencia;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(
                1L,
                "John Doe",
                LocalDate.of(1990, 1, 1),
                "12345678901",
                LocalDateTime.now());
        clienteRepository.save(cliente);

        endereco = new Endereco(
                1L,
                "Rua A",
                "Bairro A",
                "12345678",
                "Cidade A",
                "ES");
        enderecoRepository.save(endereco);

        ocorrencia = new Ocorrencia();
        ocorrencia.setCodOcorrencia(1L);
        ocorrencia.setCliente(cliente);
        ocorrencia.setEndereco(endereco);
        ocorrencia.setStaOcorrencia(StatusOcorrencia.ATIVA);
        ocorrenciaRepository.save(ocorrencia);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testExistsByCodOcorrenciaAndStaOcorrencia() {
        boolean exists = ocorrenciaRepository.existsByCodOcorrenciaAndStaOcorrencia(1L, StatusOcorrencia.ATIVA);
        assertThat(exists).isTrue();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testNotExistsByCodOcorrenciaAndStaOcorrencia() {
        boolean exists = ocorrenciaRepository.existsByCodOcorrenciaAndStaOcorrencia(1L, StatusOcorrencia.FINALIZADA);
        assertThat(exists).isFalse();
    }
}