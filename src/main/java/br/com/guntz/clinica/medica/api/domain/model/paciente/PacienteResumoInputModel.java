package br.com.guntz.clinica.medica.api.domain.model.paciente;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.validation.Valid;

public record PacienteResumoInputModel(
        String nome,

        @Valid
        Endereco endereco) {

}
