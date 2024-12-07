package br.com.guntz.clinica.medica.api.domain.model.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record ConsultaAgendamentoInputModel(
        @NotNull
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        OffsetDateTime data) {
}
