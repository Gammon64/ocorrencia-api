package com.hugo.hbs64.ocorrenciaapi.repositories;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static com.hugo.hbs64.ocorrenciaapi.entities.EnderecoConstants.ENDERECO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        ENDERECO.setCodEndereco(null);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void createEndereco_withValidData_ReturnEndereco() {
        Endereco endereco = enderecoRepository.save(ENDERECO);

        Endereco enderecoSalvo = entityManager.find(Endereco.class, endereco.getCodEndereco());

        assertThatFieldsAreEquals(endereco, enderecoSalvo);
    }

    @Test
    void createEndereco_withInvalidData_ThrowsException() {
        Endereco endereco = new Endereco();

        assertThatThrownBy(() -> enderecoRepository.save(endereco)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateEndereco_withValidData_ReturnEndereco() {
        Endereco endereco = entityManager.persistFlushFind(ENDERECO);
        endereco.setNmeLogradouro("Novo Logradouro");

        Endereco enderecoAtualizado = enderecoRepository.save(endereco);

        assertThatFieldsAreEquals(endereco, enderecoAtualizado);
    }

    @Test
    void getEndereco_withValidId_ReturnEndereco() {
        Endereco endereco = entityManager.persistFlushFind(ENDERECO);

        Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(endereco.getCodEndereco());

        assertThat(enderecoEncontrado).isNotEmpty().contains(endereco);
    }

    @Test
    void getEndereco_withInvalidId_ReturnEmpty() {
        Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(Long.MAX_VALUE);

        assertThat(enderecoEncontrado).isEmpty();
    }

    @Test
    void removeEndereco_WithExistingId_RemovesEnderecoFromDatabase() {
        Endereco endereco = entityManager.merge(ENDERECO);
        enderecoRepository.deleteById(endereco.getCodEndereco());

        Endereco sut = entityManager.find(Endereco.class, endereco.getCodEndereco());

        assertThat(sut).isNull();
    }

    private void assertThatFieldsAreEquals(Endereco base, Endereco retorno) {
        assertThat(retorno).isNotNull();
        assertThat(retorno.getCodEndereco()).isEqualTo(base.getCodEndereco());
        assertThat(retorno.getNmeLogradouro()).isEqualTo(base.getNmeLogradouro());
        assertThat(retorno.getNmeBairro()).isEqualTo(base.getNmeBairro());
        assertThat(retorno.getNroCep()).isEqualTo(base.getNroCep());
        assertThat(retorno.getNmeCidade()).isEqualTo(base.getNmeCidade());
        assertThat(retorno.getNmeEstado()).isEqualTo(base.getNmeEstado());
    }
}
