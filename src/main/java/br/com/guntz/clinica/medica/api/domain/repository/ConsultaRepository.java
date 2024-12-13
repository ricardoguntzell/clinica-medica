package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

//    List<Consulta> findAllByAtivoTrue();
}
