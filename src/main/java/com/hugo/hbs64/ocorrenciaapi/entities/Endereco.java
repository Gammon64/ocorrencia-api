package com.hugo.hbs64.ocorrenciaapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco", nullable = false)
    private Long codEndereco;

    @Column(name = "nme_logradouro", nullable = false)
    private String nmeLogradouro;

    @Column(name = "nme_bairro", nullable = false, length = 100)
    private String nmeBairro;

    @Size(min = 8, max = 8, message = "Insira apenas os números do CEP. Ex: 01111000")
    @Column(name = "nro_cep", nullable = false, length = 8)
    private String nroCep;

    @Column(name = "nme_cidade", nullable = false, length = 100)
    private String nmeCidade;

    @Size(min = 2, max = 2, message = "Insira a sigla do estado. Ex: SP")
    @Column(name = "nme_estado", nullable = false, length = 2)
    private String nmeEstado;

}