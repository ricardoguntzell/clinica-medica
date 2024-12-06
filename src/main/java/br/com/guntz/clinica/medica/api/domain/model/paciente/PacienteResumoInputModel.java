package br.com.guntz.clinica.medica.api.domain.model.paciente;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteResumoInputModel(

        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        Endereco endereco) {

}
