package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteInputModel;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteModel;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteResumoInputModel;
import br.com.guntz.clinica.medica.api.domain.repository.PacienteRepository;
import br.com.guntz.clinica.medica.api.domain.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private PacienteRepository pacienteRepository;
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteModel>> listarTodos() {
        var pacientes = pacienteRepository.findAll().stream()
                .map(PacienteModel::new)
                .toList();

        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<PacienteModel> buscarPorId(@PathVariable Long pacienteId) {
        var paciente = pacienteRepository.getReferenceById(pacienteId);

        return ResponseEntity.ok(new PacienteModel(paciente));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteModel> salvar(@Valid @RequestBody PacienteInputModel pacienteInputModel,
                                                UriComponentsBuilder uriBuilder) {
        var novoPaciente = new Paciente(pacienteInputModel);
        var pacienteSalvo = pacienteRepository.save(novoPaciente);

        var uri = uriBuilder.path("/api/pacientes/{pacienteId}").buildAndExpand(pacienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteModel(pacienteSalvo));
    }

    @PutMapping("/{pacienteId}")
    public ResponseEntity<PacienteModel> atualizar(@PathVariable Long pacienteId, @Valid @RequestBody
                                                   PacienteResumoInputModel pacienteResumoInputModel) {
        var pacienteEntradaAtualizacao = pacienteRepository.getReferenceById(pacienteId);

        pacienteService.atualizar(pacienteEntradaAtualizacao, pacienteResumoInputModel);

        return ResponseEntity.ok(new PacienteModel(pacienteEntradaAtualizacao));
    }

    @PutMapping("/{pacienteId}/ativar")
    public ResponseEntity<Object> ativar(@PathVariable Long pacienteId) {
        var paciente = pacienteRepository.getReferenceById(pacienteId);

        pacienteService.ativar(paciente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{pacienteId}/ativar")
    public ResponseEntity<Object> inativar(@PathVariable Long pacienteId) {
        var paciente = pacienteRepository.getReferenceById(pacienteId);

        pacienteService.inativar(paciente);

        return ResponseEntity.noContent().build();
    }

}
