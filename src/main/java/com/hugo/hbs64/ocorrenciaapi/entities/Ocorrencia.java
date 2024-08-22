package com.hugo.hbs64.ocorrenciaapi.entities;

import com.hugo.hbs64.ocorrenciaapi.entities.enums.StatusOcorrencia;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ocorrencia", nullable = false)
    private Long codOcorrencia;

    @NonNull
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cod_cliente", nullable = false)
    private Cliente cliente;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cod_endereco", nullable = false)
    private Endereco endereco;

    @Column(name = "dta_ocorrencia", nullable = false)
    private LocalDateTime dtaOcorrencia = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "sta_ocorrencia", columnDefinition = "status_ocorrencia", nullable = false)
    @ColumnTransformer(read = "sta_ocorrencia::varchar", write = "?::status_ocorrencia")
    private StatusOcorrencia staOcorrencia = StatusOcorrencia.ATIVA;

    @OneToMany(mappedBy = "ocorrencia")
    private List<FotoOcorrencia> fotosOcorrencia = new ArrayList<>();
}