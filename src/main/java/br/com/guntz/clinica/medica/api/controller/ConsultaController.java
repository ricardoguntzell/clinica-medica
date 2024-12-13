package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoModel;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaCancelamentoInputModel;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import br.com.guntz.clinica.medica.api.domain.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private ConsultaRepository consultaRepository;
    private ConsultaService consultaService;

//    @GetMapping
//    public ResponseEntity<List<ConsultaAgendamentoModel>> listarTodos() {
//        var consultas = consultaRepository.findAllByAtivoTrue().stream()
//                .map(ConsultaAgendamentoModel::new)
//                .toList();
//
//        return ResponseEntity.ok(consultas);
//    }

    @GetMapping("/{consultaId}")
    public ResponseEntity<ConsultaAgendamentoModel> buscarPorId(@PathVariable Long consultaId) {
        var consulta = consultaRepository.getReferenceById(consultaId);

        return ResponseEntity.ok(new ConsultaAgendamentoModel(consulta));
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
