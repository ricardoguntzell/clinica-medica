package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class MedicoAgendamentoService {

    private MedicoRepository medicoRepository;

    public Medico buscarMedicoAgendamento(Long medicoId, Especialidade especialidade, OffsetDateTime data) {
        if (medicoId == null){
            return escolherMedico(especialidade, data);
        }

        return medicoRepository.findByIdAndAtivoTrue(medicoId)
                .orElseThrow(() -> new NegocioException("Médico não localizado ou inativo para o Id informado"));
    }

    private Medico escolherMedico(Especialidade especialidade, OffsetDateTime data){
        if (especialidade == null){
            throw  new NegocioException("Especialidade é obrigatória quando médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(especialidade, data);
    }

}
