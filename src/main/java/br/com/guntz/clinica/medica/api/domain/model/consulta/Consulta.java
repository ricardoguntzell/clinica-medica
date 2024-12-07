package br.com.guntz.clinica.medica.api.domain.model.consulta;

import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;

@ToString
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consulta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    private OffsetDateTime data;
}
