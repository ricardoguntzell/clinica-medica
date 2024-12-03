package br.com.guntz.clinica.medica.api.domain.model.medico;

public record MedicoResumoModel(Long id, String nome, String telefone, String email, String crm,
                                Especialidade especialidade) {

    public MedicoResumoModel(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEmail(), medico.getCrm(),
                medico.getEspecialidade());
    }
}
