package br.com.guntz.clinica.medica.api.domain.repository;

import br.com.guntz.clinica.medica.api.domain.model.consulta.Consulta;
import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import br.com.guntz.clinica.medica.api.domain.model.medico.Especialidade;
import br.com.guntz.clinica.medica.api.domain.model.medico.Medico;
import br.com.guntz.clinica.medica.api.domain.model.medico.MedicoInputModel;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteInputModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver vázio quando único médico cadastrado não esta disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //given ou arrange
        var proximaSegundaAs10 = OffsetDateTime.of(getLocalDateTimeEscolherMedico(), ZoneOffset.ofHours(-3))
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        var medico = cadastrarMedico();

        var paciente = cadastrarPaciente();
        var consulta = cadastrarConsulta(medico, paciente, proximaSegundaAs10);
        consulta.ativar();

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(medico.getId(), null,
                proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isEmpty();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = OffsetDateTime.of(getLocalDateTimeEscolherMedico(), ZoneOffset.ofHours(-3))
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        var medico = cadastrarMedico();

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(null, Especialidade.CARDIOLOGIA,
                        proximaSegundaAs10)
                .orElse(null);

        assertThat(medicoLivre).isNotEqualTo(null);
    }

    private LocalDateTime getLocalDateTimeEscolherMedico() {
        return LocalDateTime.of(Year.now().getValue(), Month.DECEMBER, DayOfWeek.MONDAY.getValue(), 16, 0);
    }

    private Consulta cadastrarConsulta(Medico medico, Paciente paciente, OffsetDateTime data) {
        return testEntityManager.persist(new Consulta(medico, paciente, data));
    }

    private Medico cadastrarMedico() {
        var medico = new Medico(dadosMedico());
        testEntityManager.persist(medico);

        return medico;
    }

    private Paciente cadastrarPaciente() {
        var paciente = new Paciente(dadosPaciente());
        testEntityManager.persist(paciente);

        return paciente;
    }

    private MedicoInputModel dadosMedico() {
        return new MedicoInputModel(
                "Agatha Olivia Raquel",
                "11988775566",
                "agatha.raquel@clinica-medica.guntz.com.br",
                "423467",
                Especialidade.CARDIOLOGIA,
                dadosEndereco()
        );
    }

    private PacienteInputModel dadosPaciente() {
        return new PacienteInputModel(
                "Regina Luna Eliane Nogueira",
                "regina.nogueira@guntz.com.br",
                "47750545034",
                dadosEndereco()
        );
    }

    private Endereco dadosEndereco() {
        return new Endereco(
                "Travessa Corália de Siqueira",
                "São Cristóvão",
                "56503441",
                "Arcoverde",
                "PE",
                952,
                "Perto da estação trianon"
        );
    }
}