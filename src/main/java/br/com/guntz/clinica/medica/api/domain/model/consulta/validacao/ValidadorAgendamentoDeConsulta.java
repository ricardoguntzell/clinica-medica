package br.com.guntz.clinica.medica.api.domain.model.consulta.validacao;

import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;

public interface ValidadorAgendamentoDeConsulta {

    void validar(ConsultaAgendamentoInputModel dadosAgendamento);

}
