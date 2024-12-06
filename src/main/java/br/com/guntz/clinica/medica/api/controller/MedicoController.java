package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.medico.*;
import br.com.guntz.clinica.medica.api.domain.repository.MedicoRepository;
import br.com.guntz.clinica.medica.api.domain.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private MedicoRepository medicoRepository;
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<MedicoResumoModel>> listarTodos(@PageableDefault(size = 5, sort = {"nome"})
                                                               Pageable paginacao) {
        var page = medicoRepository.findAllByAtivoTrue(paginacao)
                .map(MedicoResumoModel::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{medicoId}")
    public ResponseEntity<MedicoDetalhadoModel> buscarPorId(@PathVariable Long medicoId) {
        var medicoLocalizado = medicoRepository.getReferenceById(medicoId);

        return ResponseEntity.ok(new MedicoDetalhadoModel(medicoLocalizado));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoResumoModel> salvar(@Valid @RequestBody MedicoInputModel medicoInputModel,
                                                    UriComponentsBuilder uriBuilder) {
        var medicoSalvo = medicoService.salvar(new Medico(medicoInputModel));

        var uri = uriBuilder.path("/api/medicos/{id}").buildAndExpand(medicoSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoResumoModel(medicoSalvo));
    }

    @Transactional
    @PutMapping("/{medicoId}")
    public ResponseEntity<MedicoResumoModel> atualizar(@PathVariable Long medicoId,
                                                       @Valid @RequestBody MedicoResumoInputModel medicoAtualizado) {

        var medico = medicoRepository.getReferenceById(medicoId);

        medicoService.atualizar(medico, medicoAtualizado);

        return ResponseEntity.ok(new MedicoResumoModel(medico));
    }

    @Transactional
    @DeleteMapping("/{medicoId}")
    public ResponseEntity<Object> inativar(@PathVariable Long medicoId) {
        var medico = medicoRepository.getReferenceById(medicoId);

        medicoService.inativar(medico);

        return ResponseEntity.noContent().build();
    }
}
