package br.com.guntz.clinica.medica.api.domain.model.consulta;

import br.com.guntz.clinica.medica.api.domain.model.consulta.validacao.ValidadorDataAgendamento;
import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record ConsultaAgendamentoInputModel(
        Long idMedico,

        @NotNull
        Long idPaciente,

        Especialidade especialidade,

        @NotBlank
        String dataAgendamento,

        @JsonIgnore
        OffsetDateTime data) {

    public ConsultaAgendamentoInputModel(Long idMedico, Long idPaciente, Especialidade especialidade,
                                         String dataAgendamento, OffsetDateTime data) {
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.especialidade = especialidade;
        this.dataAgendamento = dataAgendamento;

        this.data = ValidadorDataAgendamento.validar(dataAgendamento);
    }
}
