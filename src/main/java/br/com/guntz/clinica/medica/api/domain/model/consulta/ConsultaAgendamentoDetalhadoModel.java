package br.com.guntz.clinica.medica.api.domain.model.consulta;

import java.time.OffsetDateTime;

public record ConsultaAgendamentoDetalhadoModel(Long id, Long idMedico, Long idPaciente, OffsetDateTime data,
                                                String motivoCancelamento, OffsetDateTime dataCancelamento,
                                                boolean ativo) {

    public ConsultaAgendamentoDetalhadoModel(Consulta consultaAgendada) {
        this(consultaAgendada.getId(), consultaAgendada.getMedico().getId(), consultaAgendada.getPaciente().getId(),
                consultaAgendada.getData(), consultaAgendada.getMotivoCancelamento(),
                consultaAgendada.getDataCancelamento(), consultaAgendada.isAtivo());
    }
}
