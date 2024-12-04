package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.usuario.UsuarioInputModel;
import br.com.guntz.clinica.medica.api.domain.model.token.TokenJWTModel;
import br.com.guntz.clinica.medica.api.domain.model.usuario.Usuario;
import br.com.guntz.clinica.medica.api.domain.service.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> realizarLogin(@Valid @RequestBody UsuarioInputModel usuarioInputModel) {
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(usuarioInputModel.usuario(),
                usuarioInputModel.senha());

        var autenticacao = authenticationManager.authenticate(autenticacaoToken);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new TokenJWTModel(tokenJWT));
    }

}
