package br.com.guntz.clinica.medica.api.domain.model.consulta;

import jakarta.validation.constraints.NotBlank;

public record ConsultaCancelamentoInputModel(

        @NotBlank
        String motivoCancelamento) {

}
