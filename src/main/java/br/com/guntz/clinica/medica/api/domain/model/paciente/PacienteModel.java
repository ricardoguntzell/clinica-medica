package br.com.guntz.clinica.medica.api.domain.model.paciente;

public record PacienteModel(Long id, String nome, String email, String cpf, Integer ativo) {

    public PacienteModel(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getAtivo());
    }

}
