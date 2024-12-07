package br.com.guntz.clinica.medica.api.domain.model.consulta.validacao;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica  implements ValidadorAgendamentoDeConsulta{

    public void validar(ConsultaAgendamentoInputModel dadosConsulta) {
        var dataConsulta = dadosConsulta.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        var minutos = dadosConsulta.data().getMinute() != 0;
        var segundos = dadosConsulta.data().getSecond() != 0;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new NegocioException("Consulta fora do horário de funcionamento da clínica médica");
        }

        if (minutos || segundos) {
            throw new NegocioException("Consulta deve ser informada com minutos e segundos zerados");
        }
    }

}