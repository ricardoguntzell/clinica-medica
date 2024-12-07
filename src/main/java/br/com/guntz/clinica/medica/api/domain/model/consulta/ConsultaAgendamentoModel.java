package br.com.guntz.clinica.medica.api.domain.model.consulta;

import java.time.OffsetDateTime;

public record ConsultaAgendamentoModel(Long id, Long idMedico, Long idPaciente, OffsetDateTime data) {

    public ConsultaAgendamentoModel(Consulta consultaAgendada) {
        this(consultaAgendada.getId(), consultaAgendada.getMedico().getId(), consultaAgendada.getPaciente().getId(),
                consultaAgendada.getData());
    }
}
