package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.medico.MedicoResumoInputModel;
import br.com.guntz.clinica.medica.api.domain.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class MedicoService {

    private MedicoRepository medicoRepository;

    @Transactional
    public Medico salvar(Medico novoMedico) {
        verificaCrmExistente(novoMedico.getCrm());
        verificaEmailExistente(novoMedico.getEmail());

        return medicoRepository.save(novoMedico);
    }

    private void verificaEmailExistente(String email) {
        var emailEmUso = medicoRepository.existsByEmail(email);

        if (emailEmUso) {
            throw new NegocioException("Email já está em uso");
        }
    }

    private void verificaCrmExistente(String crm) {
        var crmEmUso = medicoRepository.existsByCrm(crm);

        if (crmEmUso) {
            throw new NegocioException("CRM já está em uso");
        }
    }

    public void atualizar(Medico medicoAtual, MedicoResumoInputModel medicoAtualizado) {
        medicoAtual.atualizar(medicoAtualizado);
    }

    public void inativar(Medico medico) {
        medico.inativar();
    }
}
