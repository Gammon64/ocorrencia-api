package com.hugo.hbs64.ocorrenciaapi.repositories.specifications;

import com.hugo.hbs64.ocorrenciaapi.entities.Ocorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FiltroOcorrenciaDTO;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaSpecs {

    private OcorrenciaSpecs() {
    }

    public static Specification<Ocorrencia> filtroOcorrenciaSpec(FiltroOcorrenciaDTO filtroOcorrenciaDTO) {
        return (root, query, criteriaBuilder) -> {

            final String CLIENTE = "cliente";
            final String ENDERECO = "endereco";

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotEmpty(filtroOcorrenciaDTO.nmeCliente())) {
                predicates.add(criteriaBuilder.like(root.get(CLIENTE).get("nmeCliente"), "%" + filtroOcorrenciaDTO.nmeCliente() + "%"));
            }
            if (StringUtils.isNotEmpty(filtroOcorrenciaDTO.nroCpf())) {
                predicates.add(criteriaBuilder.like(root.get(CLIENTE).get("nroCpf"), "%" + filtroOcorrenciaDTO.nroCpf() + "%"));
            }
            if (filtroOcorrenciaDTO.dtaOcorrencia() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dtaOcorrencia").as(LocalDate.class), filtroOcorrenciaDTO.dtaOcorrencia()));
            }
            if (StringUtils.isNotEmpty(filtroOcorrenciaDTO.nmeCidade())) {
                predicates.add(criteriaBuilder.like(root.get(ENDERECO).get("nmeCidade"), "%" + filtroOcorrenciaDTO.nmeCidade() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
