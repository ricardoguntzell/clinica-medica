package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByUsuario(String usuario);

    UserDetails findByUsuarioAndAtivo(String usuario, boolean ativo);
}
