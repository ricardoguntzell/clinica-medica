package br.com.guntz.clinica.medica.api.domain.model.consulta;

import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record ConsultaAgendamentoInputModel(
        Long idMedico,

        @NotNull
        Long idPaciente,

        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssz", timezone = "America/Sao_Paulo")
        OffsetDateTime data) {

}
