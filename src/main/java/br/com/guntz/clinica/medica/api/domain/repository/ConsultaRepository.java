package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
