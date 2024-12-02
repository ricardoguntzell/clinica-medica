package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.repository.MedicoRepository;
import br.com.guntz.clinica.medica.api.model.input.MedicoInputModel;
import br.com.guntz.clinica.medica.api.model.input.MedicoResumoInputModel;
import br.com.guntz.clinica.medica.api.model.output.MedicoResumoModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private MedicoRepository medicoRepository;

    @GetMapping
    public Page<MedicoResumoModel> listarTodos(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAll(paginacao)
                .map(MedicoResumoModel::new);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody MedicoInputModel medicoInputModel) {
        medicoRepository.save(new Medico(medicoInputModel));
    }

    @Transactional
    @PutMapping("/{medicoId}")
    public void atualizar(@PathVariable Long medicoId,
                          @RequestBody MedicoResumoInputModel medicoResumoInputModel) {
        var medicoEntrada = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Id não localizado"));

        medicoEntrada.setId(medicoId);
        medicoEntrada.atualizar(medicoResumoInputModel);
    }

}
