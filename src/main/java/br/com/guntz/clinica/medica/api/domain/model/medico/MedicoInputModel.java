package br.com.guntz.clinica.medica.api.domain.model.medico;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoInputModel(

        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank(message = "{telefone.obrigatorio}")
        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @Email(message = "{email.invalido}")
        String email,

        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,

        @NotNull(message = "{especialidade.obrigatoria}")
        Especialidade especialidade,

        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        Endereco endereco) {

}
