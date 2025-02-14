package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.validacao.ValidadorAgendamentoDeConsulta;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ConsultaService {

    private ConsultaRepository consultaRepository;
    private MedicoAgendamentoService medicoAgendamentoService;
    private PacienteAgendamentoService pacienteAgendamentoService;
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Transactional
    public Consulta agendar(ConsultaAgendamentoInputModel dadosConsultaEntrada) {
        var paciente = pacienteAgendamentoService.buscarPacienteAgendamento(dadosConsultaEntrada.idPaciente());
        var medico = medicoAgendamentoService.buscarMedicoAgendamento(dadosConsultaEntrada);

        validadores.forEach(v -> v.validar(dadosConsultaEntrada));

        var consulta = new Consulta(medico, paciente, dadosConsultaEntrada.data());
        consulta.ativar();
        
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void cancelar(Consulta consulta, String motivoCancelamento) {
        consulta.cancelar(motivoCancelamento);
    }
}
