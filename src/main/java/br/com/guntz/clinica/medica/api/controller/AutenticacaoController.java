package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.usuario.AutenticacaoInputModel;
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

    @PostMapping
    public ResponseEntity<Object> realizarLogin(@Valid @RequestBody AutenticacaoInputModel autenticacaoInputModel) {
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(autenticacaoInputModel.usuario(),
                autenticacaoInputModel.senha());

        var autenticacao = authenticationManager.authenticate(autenticacaoToken);

        return ResponseEntity.ok().build();
    }

}
