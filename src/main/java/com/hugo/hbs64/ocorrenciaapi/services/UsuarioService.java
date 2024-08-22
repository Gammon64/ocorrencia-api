package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Usuario;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.UsuarioDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.mappers.UsuarioMapper;
import com.hugo.hbs64.ocorrenciaapi.exceptions.UsuarioDuplicadoException;
import com.hugo.hbs64.ocorrenciaapi.repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public UsuarioService(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Usuario create(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByLogin(usuarioDTO.login())) {
            throw new UsuarioDuplicadoException("Usuário já cadastrado");
        }
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public String authenticate(UsuarioDTO usuarioDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.login(), usuarioDTO.senha());
        Authentication autentication = authenticationManager.authenticate(authenticationToken);

        return tokenService.gerarToken(autentication.getName());
    }
}
