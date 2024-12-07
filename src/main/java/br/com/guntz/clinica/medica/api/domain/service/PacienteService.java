package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteResumoInputModel;
import br.com.guntz.clinica.medica.api.domain.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    @Transactional
    public Paciente salvar(Paciente novoPaciente) {
        verificaEmailExistente(novoPaciente.getEmail());
        verificaCpfExistente(novoPaciente.getCpf());

        novoPaciente.ativar();

        return pacienteRepository.save(novoPaciente);
    }

    @Transactional
    public void atualizar(Paciente paciente, PacienteResumoInputModel pacienteEntradaAtualizacao) {
        paciente.atualizar(pacienteEntradaAtualizacao);
    }


    @Transactional
    public void inativar(Paciente paciente) {
        paciente.inativar();
    }

    @Transactional
    public void ativar(Paciente paciente) {
        paciente.ativar();
    }

    private void verificaEmailExistente(String email) {
        var emailEmUso = pacienteRepository.existsByEmail(email);

        if (emailEmUso) {
            throw new NegocioException("Email já está em uso");
        }
    }

    private void verificaCpfExistente(String cpf) {
        var cpfEmUso = pacienteRepository.existsByCpf(cpf);

        if (cpfEmUso) {
            throw new NegocioException("CPF já está em uso");
        }
    }

    public Paciente buscarPacienteAgendamento(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new NegocioException("Paciente não localizado para o Id informado"));
    }
}
