package br.com.guntz.clinica.medica.api.domain.model.consulta.validacao;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validar(ConsultaAgendamentoInputModel dadosConsulta) {
        var dataConsulta = dadosConsulta.data();
        var dataAgora = OffsetDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAgora,dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new NegocioException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
    }
}
