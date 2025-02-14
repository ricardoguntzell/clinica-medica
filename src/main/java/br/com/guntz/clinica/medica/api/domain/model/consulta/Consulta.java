package br.com.guntz.clinica.medica.api.domain.model.consulta;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@EqualsAndHashCode(of = "id")
@Setter
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

    private boolean ativo;

    private String motivoCancelamento;

    private OffsetDateTime dataCancelamento;

    public Consulta(Medico medico, Paciente paciente, OffsetDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }

    public void ativar() {
        if (estaAtivo()) {
            throw new NegocioException("consulta já está ativa");
        }

        setAtivo(true);
    }

    public void cancelar(String motivoCancelamento) {
        if (estaInativo()) {
            throw new NegocioException("consulta já foi cancelada");
        }
        setAtivo(false);
        setMotivoCancelamento(motivoCancelamento);
        setDataCancelamento(OffsetDateTime.now());
    }

    private boolean estaAtivo() {
        return isAtivo();
    }

    private boolean estaInativo() {
        return !estaAtivo();
    }
}
