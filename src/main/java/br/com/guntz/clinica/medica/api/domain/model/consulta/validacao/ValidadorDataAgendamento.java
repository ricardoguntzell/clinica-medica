package br.com.guntz.clinica.medica.api.domain.model.consulta.validacao;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.consulta.ConsultaAgendamentoInputModel;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Locale;

@Component
public class ValidadorDataAgendamento {

    public static OffsetDateTime validar(String dataAgendamento) {
        try {
            var localDateTime = LocalDateTime.parse(dataAgendamento,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH", new Locale("pt", "BR")));

//            return OffsetDateTime.now().plusHours(20);
            return OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-3));

            //ZondedDateTime
        } catch (Exception e) {
            throw new NegocioException("A data deve corresponder ao seguinte formato: yyyy-MM-dd'T'HH");
        }
    }
}
