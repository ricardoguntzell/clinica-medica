package br.com.guntz.clinica.medica.api.domain.model.medico;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoResumoInputModel(
        String nome,

        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @Valid
        Endereco endereco) {

}
