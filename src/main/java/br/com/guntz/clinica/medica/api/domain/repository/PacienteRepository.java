package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    List<Paciente> findAllByAtivoTrue();

    Optional<Paciente> findByIdAndAtivoTrue(Long id);
}
