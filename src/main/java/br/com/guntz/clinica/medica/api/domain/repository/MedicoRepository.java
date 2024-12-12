package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByEmail(String email);

    boolean existsByCrm(String crm);

    @Query("""
            SELECT
                m
            FROM
                Medico m
            WHERE
                m.ativo = true
                    AND (m.especialidade = :especialidade OR m.id = :idMedico)
                    AND m.id NOT IN (SELECT
                        c.medico.id
                    FROM
                        Consulta c
                    WHERE
                        c.data = :data)
            ORDER BY RAND()
            LIMIT 1
            """)
    Optional<Medico> escolherMedicoAleatorioLivreNaData(Long idMedico, Especialidade especialidade, OffsetDateTime data);

    Optional<Medico> findByIdAndAtivoTrue(Long id);
}
