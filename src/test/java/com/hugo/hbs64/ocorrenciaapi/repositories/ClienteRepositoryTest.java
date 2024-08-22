package com.hugo.hbs64.ocorrenciaapi.repositories;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static com.hugo.hbs64.ocorrenciaapi.entities.ClienteConstants.CLIENTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        // Impede o entityManager de devolver objeto desanexado por engano
        CLIENTE.setCodCliente(null);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void createCliente_withValidData_ReturnCliente() {
        Cliente cliente = clienteRepository.save(CLIENTE);

        Cliente clienteSalvo = entityManager.find(Cliente.class, cliente.getCodCliente());

        assertThatFieldsAreEquals(cliente, clienteSalvo);
    }

    @Test
    void createCliente_withInvalidData_ThrowsException() {
        Cliente cliente = new Cliente();

        assertThatThrownBy(() -> clienteRepository.save(cliente)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void createCliente_withExistingCpf_ThrowsException() {
        entityManager.persistAndFlush(CLIENTE);
        entityManager.detach(CLIENTE);
        CLIENTE.setCodCliente(null);
        CLIENTE.setNroCpf("22222222222");

        assertThatThrownBy(() -> clienteRepository.save(CLIENTE)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateCliente_withValidData_ReturnCliente() {
        Cliente cliente = entityManager.persistFlushFind(CLIENTE);
        cliente.setNmeCliente("Novo Nome");

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        assertThatFieldsAreEquals(cliente, clienteAtualizado);
    }

    @Test
    void getCliente_withValidId_ReturnCliente() {
        Cliente cliente = entityManager.persistFlushFind(CLIENTE);

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getCodCliente());

        assertThat(clienteEncontrado).isNotEmpty().contains(cliente);
    }

    @Test
    void getCliente_withInvalidId_ReturnEmpty() {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(Long.MAX_VALUE);

        assertThat(clienteEncontrado).isEmpty();
    }

    @Test
    void removeCliente_WithExistingId_RemovesClienteFromDatabase() {
        Cliente cliente = entityManager.merge(CLIENTE);
        clienteRepository.deleteById(cliente.getCodCliente());

        Cliente sut = entityManager.find(Cliente.class, cliente.getCodCliente());

        assertThat(sut).isNull();
    }

    private static void assertThatFieldsAreEquals(Cliente base, Cliente retorno) {
        assertThat(retorno).isNotNull();
        assertThat(retorno.getNmeCliente()).isEqualTo(base.getNmeCliente());
        assertThat(retorno.getDtaNascimento()).isEqualTo(base.getDtaNascimento());
        assertThat(retorno.getNroCpf()).isEqualTo(base.getNroCpf());
        assertThat(retorno.getDtaCriacao()).isEqualTo(base.getDtaCriacao());
    }
}
