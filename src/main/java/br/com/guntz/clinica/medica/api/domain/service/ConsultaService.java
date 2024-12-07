package br.com.guntz.clinica.medica.api.domain.service;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import br.com.guntz.clinica.medica.api.domain.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConsultaService {

    private ConsultaRepository consultaRepository;

    @Transactional
    public Consulta agendar(Consulta novaConsulta) {
        return consultaRepository.save(novaConsulta);
    }
}
