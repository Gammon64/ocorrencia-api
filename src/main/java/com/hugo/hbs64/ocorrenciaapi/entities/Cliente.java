package com.hugo.hbs64.ocorrenciaapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente", nullable = false)
    private Long codCliente;

    @Column(name = "nme_cliente", nullable = false, length = 100)
    private String nmeCliente;

    @Column(name = "dta_nascimento")
    private LocalDate dtaNascimento;

    @Column(name = "nro_cpf", nullable = false, length = 11, unique = true)
    private String nroCpf;

    @Column(name = "dta_criacao", columnDefinition = "default current_timestamp")
    private LocalDateTime dtaCriacao;

}