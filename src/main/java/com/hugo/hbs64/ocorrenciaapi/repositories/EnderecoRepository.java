package com.hugo.hbs64.ocorrenciaapi.repositories;

import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByNmeLogradouroAndNmeBairroAndNroCep(String nmeLogradouro, String nmeBairro, String nroCep);
}
