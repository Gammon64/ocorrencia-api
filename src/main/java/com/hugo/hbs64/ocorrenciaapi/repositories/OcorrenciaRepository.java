package com.hugo.hbs64.ocorrenciaapi.repositories;

import com.hugo.hbs64.ocorrenciaapi.entities.Ocorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.enums.StatusOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>, JpaSpecificationExecutor<Ocorrencia> {
    boolean existsByCodOcorrenciaAndStaOcorrencia(Long codOcorrencia, StatusOcorrencia statusOcorrencia);
}
