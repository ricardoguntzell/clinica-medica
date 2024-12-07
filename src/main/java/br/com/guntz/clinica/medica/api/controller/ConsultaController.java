package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoModel;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import br.com.guntz.clinica.medica.api.domain.service.ConsultaService;
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

    @PostMapping
    public ResponseEntity<Object> agendar(@Valid @RequestBody ConsultaAgendamentoInputModel consultaAgendamento) {
        var consultaAgendada = consultaService.agendar(consultaAgendamento);

        return ResponseEntity.ok(new ConsultaAgendamentoModel(consultaAgendada));
    }

}
