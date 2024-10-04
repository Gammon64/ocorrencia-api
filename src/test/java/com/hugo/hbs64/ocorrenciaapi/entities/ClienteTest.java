package com.hugo.hbs64.ocorrenciaapi.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteTest {

    @Test
    public void testClienteConstructorAndGetters() {
        Long codCliente = 1L;
        String nmeCliente = "John Doe";
        LocalDate dtaNascimento = LocalDate.of(1990, 1, 1);
        String nroCpf = "12345678901";
        LocalDateTime dtaCriacao = LocalDateTime.now();

        Cliente cliente = new Cliente(codCliente, nmeCliente, dtaNascimento, nroCpf, dtaCriacao);

        assertEquals(codCliente, cliente.getCodCliente());
        assertEquals(nmeCliente, cliente.getNmeCliente());
        assertEquals(dtaNascimento, cliente.getDtaNascimento());
        assertEquals(nroCpf, cliente.getNroCpf());
        assertEquals(dtaCriacao, cliente.getDtaCriacao());
    }

    @Test
    public void testClienteSetters() {
        Cliente cliente = new Cliente();

        Long codCliente = 1L;
        String nmeCliente = "Jane Doe";
        LocalDate dtaNascimento = LocalDate.of(1992, 2, 2);
        String nroCpf = "09876543210";
        LocalDateTime dtaCriacao = LocalDateTime.now();

        cliente.setCodCliente(codCliente);
        cliente.setNmeCliente(nmeCliente);
        cliente.setDtaNascimento(dtaNascimento);
        cliente.setNroCpf(nroCpf);
        cliente.setDtaCriacao(dtaCriacao);

        assertEquals(codCliente, cliente.getCodCliente());
        assertEquals(nmeCliente, cliente.getNmeCliente());
        assertEquals(dtaNascimento, cliente.getDtaNascimento());
        assertEquals(nroCpf, cliente.getNroCpf());
        assertEquals(dtaCriacao, cliente.getDtaCriacao());
    }

    @Test
    public void testClienteNoArgsConstructor() {
        Cliente cliente = new Cliente();

        assertNull(cliente.getCodCliente());
        assertNull(cliente.getNmeCliente());
        assertNull(cliente.getDtaNascimento());
        assertNull(cliente.getNroCpf());
        assertNull(cliente.getDtaCriacao());
    }
}
