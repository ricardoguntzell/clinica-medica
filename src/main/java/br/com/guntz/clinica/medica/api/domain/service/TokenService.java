package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretToken;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritimo = Algorithm.HMAC256(secretToken);

            return JWT.create()
                    .withIssuer("API Clinica Médica")
                    .withSubject(usuario.getUsuario())
                    .withClaim("id", usuario.getId())
                    .withClaim("role", "xpto")
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("erro ao gerar token JWT. ", ex);
        }
    }

    private Instant dataExpiracao() {
        return OffsetDateTime.now()
                .plusHours(2)
                .toInstant();
    }

}
