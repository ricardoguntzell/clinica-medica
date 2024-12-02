package br.com.guntz.clinica.medica.api.model.input;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoInputModel(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        Endereco endereco) {

}
