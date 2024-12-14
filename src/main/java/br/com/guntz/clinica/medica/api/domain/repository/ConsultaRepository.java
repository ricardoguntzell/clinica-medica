package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByAtivo(Integer ativo);
}
