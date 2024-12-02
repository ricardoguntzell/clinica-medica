package br.com.guntz.clinica.medica.api.model.output;

import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;

public record MedicoResumoModel(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public MedicoResumoModel(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
