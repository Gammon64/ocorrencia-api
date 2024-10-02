package com.hugo.hbs64.ocorrenciaapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNmeCliente("John Doe");
        cliente.setNroCpf("12345678901");
        clienteRepository.save(cliente);
    }

    @Test
    void testFindByNmeClienteAndNroCpf() {
        Optional<Cliente> foundCliente = clienteRepository.findByNmeClienteAndNroCpf("John Doe", "12345678901");
        assertThat(foundCliente).isPresent();
        assertThat(foundCliente.get().getNmeCliente()).isEqualTo("John Doe");
        assertThat(foundCliente.get().getNroCpf()).isEqualTo("12345678901");
    }

    @Test
    void testExistsByNroCpf() {
        boolean exists = clienteRepository.existsByNroCpf("12345678901");
        assertThat(exists).isTrue();
    }

    @Test
    void testNotExistsByNroCpf() {
        boolean exists = clienteRepository.existsByNroCpf("09876543210");
        assertThat(exists).isFalse();
    }
}