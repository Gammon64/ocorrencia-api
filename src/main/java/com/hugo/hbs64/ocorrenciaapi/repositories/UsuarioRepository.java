package com.hugo.hbs64.ocorrenciaapi.repositories;

import com.hugo.hbs64.ocorrenciaapi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByLogin(String login);

    Optional<Usuario> findByLogin(String login);
}
