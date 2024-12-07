package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import br.com.guntz.clinica.medica.api.domain.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PacienteAgendamentoService {

    private PacienteRepository pacienteRepository;

    public Paciente buscarPacienteAgendamento(Long pacienteId) {
        return pacienteRepository.findByIdAndAtivoTrue(pacienteId)
                .orElseThrow(() -> new NegocioException("Paciente não localizado ou inativo para o Id informado"));
    }

}
