package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteInputModel;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteModel;
import br.com.guntz.clinica.medica.api.domain.repository.PacienteRepository;
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
        novoPaciente.ativar();

        pacienteRepository.findByEmail(pacienteInputModel.email())
                .orElseThrow(() -> new NegocioException("Email já existente"));

        var pacienteSalvo = pacienteRepository.save(novoPaciente);
        var uri = uriBuilder.path("/api/pacientes/{pacienteId}").buildAndExpand(pacienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteModel(pacienteSalvo));
    }


}
