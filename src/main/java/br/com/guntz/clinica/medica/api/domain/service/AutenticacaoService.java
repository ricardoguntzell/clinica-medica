package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        return usuarioRepository.findByUsuarioAndAtivo(usuario, true);
    }
}
