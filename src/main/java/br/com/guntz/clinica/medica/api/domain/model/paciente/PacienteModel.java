package br.com.guntz.clinica.medica.api.domain.model.paciente;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;

public record PacienteModel(Long id, String nome, String email, String cpf, Endereco endereco) {

    public PacienteModel(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }

}
