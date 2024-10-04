package com.hugo.hbs64.ocorrenciaapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;

@DataJpaTest
@ActiveProfiles("test")
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco(
                null,
                "Rua A",
                "Bairro B",
                "12345678",
                "Cidade C",
                "SP");
        enderecoRepository.save(endereco);
    }

    @Test
    public void testFindByNmeLogradouroAndNmeBairroAndNroCep() {
        String nmeLogradouro = "Rua A";
        String nmeBairro = "Bairro B";
        String nroCep = "12345678";

        Optional<Endereco> foundEndereco = enderecoRepository.findByNmeLogradouroAndNmeBairroAndNroCep(nmeLogradouro,
                nmeBairro, nroCep);
        assertThat(foundEndereco).isPresent();
        assertThat(foundEndereco.get().getNmeLogradouro()).isEqualTo(nmeLogradouro);
        assertThat(foundEndereco.get().getNmeBairro()).isEqualTo(nmeBairro);
        assertThat(foundEndereco.get().getNroCep()).isEqualTo(nroCep);
    }
}