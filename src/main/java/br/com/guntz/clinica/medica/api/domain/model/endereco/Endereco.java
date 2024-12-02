package br.com.guntz.clinica.medica.api.domain.model.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Embeddable
public class Endereco {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    @NotNull
    private Integer numero;

    private String complemento;

}
