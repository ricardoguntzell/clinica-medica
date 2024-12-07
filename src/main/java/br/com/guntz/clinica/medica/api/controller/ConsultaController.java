package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoModel;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import br.com.guntz.clinica.medica.api.domain.repository.MedicoRepository;
import br.com.guntz.clinica.medica.api.domain.repository.PacienteRepository;
import br.com.guntz.clinica.medica.api.domain.service.ConsultaService;
import br.com.guntz.clinica.medica.api.domain.service.MedicoService;
import br.com.guntz.clinica.medica.api.domain.service.PacienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private ConsultaRepository consultaRepository;
    private ConsultaService consultaService;
    private MedicoService medicoService;
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Object> agendar(@Valid @RequestBody ConsultaAgendamentoInputModel consultaAgendamento){
        var medico = medicoService.buscarMedicoAgendamento(consultaAgendamento.idMedico());
        var paciente = pacienteService.buscarPacienteAgendamento(consultaAgendamento.idPaciente());

        var novaConsulta = new Consulta(null, medico, paciente ,consultaAgendamento.data());

        var consultaAgendada = consultaService.agendar(novaConsulta);

        return ResponseEntity.ok(new ConsultaAgendamentoModel(consultaAgendada));
    }

}
