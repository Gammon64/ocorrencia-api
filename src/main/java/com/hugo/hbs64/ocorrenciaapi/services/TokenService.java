package com.hugo.hbs64.ocorrenciaapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Logger;

@Service
public class TokenService {

    private static final Logger LOGGER = Logger.getLogger(TokenService.class.getName());

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(String login) {
        //withClaim utilizado para adicionar campos ao JWT
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ocorrenciaapi")
                    .withSubject(login)
                    .withExpiresAt(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00")))
                    .withClaim("login", login)
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            LOGGER.severe("Erro ao gerar token jwt");
            return null;
        }
    }

    public String getSubject(String token) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("ocorrenciaapi")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            LOGGER.severe("Erro ao gerar token jwt");
            return null;
        }
    }
}
