package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.validacao.ValidadorAgendamentoDeConsulta;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@AllArgsConstructor
@Service
public class ConsultaService {

    private ConsultaRepository consultaRepository;
    private MedicoAgendamentoService medicoAgendamentoService;
    private PacienteAgendamentoService pacienteAgendamentoService;
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Transactional
    public Consulta agendar(ConsultaAgendamentoInputModel dadosNovaConsulta) {
        var paciente = pacienteAgendamentoService.buscarPacienteAgendamento(dadosNovaConsulta.idPaciente());

        var medico = medicoAgendamentoService.buscarMedicoAgendamento(dadosNovaConsulta.idMedico(),
                dadosNovaConsulta.especialidade(), dadosNovaConsulta.data());

        validadores.forEach(v -> v.validar(dadosNovaConsulta));

        var consulta = new Consulta(medico, paciente, dadosNovaConsulta.data());

        return consultaRepository.save(consulta);
    }
}
