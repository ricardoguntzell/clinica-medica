package br.com.guntz.clinica.medica.api.domain.model.consulta;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@ToString
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

    private Integer ativo = 0;

    private String motivoCancelamento;

    private OffsetDateTime dataCancelamento;

    public Consulta(Medico medico, Paciente paciente, OffsetDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }
    public void ativar() {
        if (estaAtivo()) {
            throw new NegocioException("Médico já está ativo");
        }

        setAtivo(1);
    }

    public void inativar() {
        if (estaInativo()) {
            throw new NegocioException("Médico já está inativo");
        }
        setAtivo(0);
    }

    private boolean estaAtivo() {
        return getAtivo().equals(1);
    }

    private boolean estaInativo() {
        return !estaAtivo();
    }
}
