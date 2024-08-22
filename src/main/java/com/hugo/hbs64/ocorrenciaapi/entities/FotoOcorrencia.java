package com.hugo.hbs64.ocorrenciaapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@DynamicInsert
@Entity
@Table(name = "foto_ocorrencia")
public class FotoOcorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_foto_ocorrencia", nullable = false)
    private Long codFotoOcorrencia;

    @NonNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cod_ocorrencia", nullable = false)
    private Ocorrencia ocorrencia;

    @Column(name = "dta_criacao", columnDefinition = "default current_timestamp")
    private LocalDateTime dtaCriacao;

    @NonNull
    @Column(name = "dsc_path_bucket", nullable = false, length = 100)
    private String dscPathBucket;

    @NonNull
    @Column(name = "dsc_hash", nullable = false)
    private String dscHash;

    @Transient
    private String url;

}