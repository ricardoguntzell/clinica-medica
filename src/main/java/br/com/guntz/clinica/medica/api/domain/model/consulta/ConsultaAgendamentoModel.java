package br.com.guntz.clinica.medica.api.domain.model.consulta;

public record ConsultaAgendamentoModel(Long id) {

    public ConsultaAgendamentoModel(Consulta consultaAgendada) {
        this(consultaAgendada.getId());
    }
}
