package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
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

    public Medico buscarMedicoAgendamento(ConsultaAgendamentoInputModel dadosConsultaEntrada) {
        if (dadosConsultaEntrada.idMedico() != null) {
            var medico = medicoRepository.findByIdAndAtivoTrue(dadosConsultaEntrada.idMedico())
                    .orElseThrow(() -> new NegocioException("Médico não localizado ou inativo para o Id informado"));

            return escolherMedico(medico.getId(), null, dadosConsultaEntrada.data());
        }

        return escolherMedico(null, dadosConsultaEntrada.especialidade(), dadosConsultaEntrada.data());
    }

    private Medico escolherMedico(Long medicoId, Especialidade especialidade, OffsetDateTime data) {
        if (medicoId == null && especialidade == null) {
            throw new NegocioException("Especialidade é obrigatória quando médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(medicoId, especialidade, data)
                .orElseThrow(() -> new NegocioException("Data indisponível para agendamento com médico ou especialidade informada"));
    }

}
