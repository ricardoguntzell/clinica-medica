package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoDetalhadoModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaCancelamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import br.com.guntz.clinica.medica.api.domain.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@AllArgsConstructor
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaAgendamentoModel>> listarTodos() {
        var consultas = consultaRepository.findAllByAtivo(true)
                .stream()
                .map(ConsultaAgendamentoModel::new)
                .toList();

        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{consultaId}")
    public ResponseEntity<ConsultaAgendamentoDetalhadoModel> buscarPorId(@PathVariable Long consultaId) {
        var consulta = consultaRepository.getReferenceById(consultaId);

        return ResponseEntity.ok(new ConsultaAgendamentoDetalhadoModel(consulta));
    }

    @PostMapping
    public ResponseEntity<Object> agendar(@Valid @RequestBody ConsultaAgendamentoInputModel consultaAgendamento) {
        var consultaAgendada = consultaService.agendar(consultaAgendamento);

        var consultaAgendamentoModel = new ConsultaAgendamentoModel(consultaAgendada);

        return ResponseEntity.ok(consultaAgendamentoModel);
    }

    @DeleteMapping("/{consultaId}")
    public ResponseEntity<Object> cancelar(@PathVariable Long consultaId,
                                           @Valid @RequestBody ConsultaCancelamentoInputModel dadosCancelamento) {
        var consulta = consultaRepository.getReferenceById(consultaId);
        consultaService.cancelar(consulta, dadosCancelamento.motivoCancelamento());

        return ResponseEntity.noContent().build();
    }


}
