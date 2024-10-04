package com.hugo.hbs64.ocorrenciaapi.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnderecoTest {

    @Test
    public void testEnderecoConstructorAndGetters() {
        Long codEndereco = 1L;
        String nmeLogradouro = "Rua Exemplo";
        String nmeBairro = "Bairro Exemplo";
        String nroCep = "12345678";
        String nmeCidade = "Cidade Exemplo";
        String nmeEstado = "SP";

        Endereco endereco = new Endereco(codEndereco, nmeLogradouro, nmeBairro, nroCep, nmeCidade, nmeEstado);

        assertEquals(codEndereco, endereco.getCodEndereco());
        assertEquals(nmeLogradouro, endereco.getNmeLogradouro());
        assertEquals(nmeBairro, endereco.getNmeBairro());
        assertEquals(nroCep, endereco.getNroCep());
        assertEquals(nmeCidade, endereco.getNmeCidade());
        assertEquals(nmeEstado, endereco.getNmeEstado());
    }

    @Test
    public void testEnderecoSetters() {
        Endereco endereco = new Endereco();

        Long codEndereco = 2L;
        String nmeLogradouro = "Avenida Teste";
        String nmeBairro = "Bairro Teste";
        String nroCep = "87654321";
        String nmeCidade = "Cidade Teste";
        String nmeEstado = "RJ";

        endereco.setCodEndereco(codEndereco);
        endereco.setNmeLogradouro(nmeLogradouro);
        endereco.setNmeBairro(nmeBairro);
        endereco.setNroCep(nroCep);
        endereco.setNmeCidade(nmeCidade);
        endereco.setNmeEstado(nmeEstado);

        assertEquals(codEndereco, endereco.getCodEndereco());
        assertEquals(nmeLogradouro, endereco.getNmeLogradouro());
        assertEquals(nmeBairro, endereco.getNmeBairro());
        assertEquals(nroCep, endereco.getNroCep());
        assertEquals(nmeCidade, endereco.getNmeCidade());
        assertEquals(nmeEstado, endereco.getNmeEstado());
    }

    @Test
    public void testEnderecoValidation() {
        Endereco endereco = new Endereco();

        // Test invalid CEP
        String invalidCep = "123";
        endereco.setNroCep(invalidCep);
        assertFalse(endereco.getNroCep().length() == 8);

        // Test valid CEP
        String validCep = "12345678";
        endereco.setNroCep(validCep);
        assertTrue(endereco.getNroCep().length() == 8);

        // Test invalid Estado
        String invalidEstado = "Sao Paulo";
        endereco.setNmeEstado(invalidEstado);
        assertFalse(endereco.getNmeEstado().length() == 2);

        // Test valid Estado
        String validEstado = "SP";
        endereco.setNmeEstado(validEstado);
        assertTrue(endereco.getNmeEstado().length() == 2);
    }
}