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
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaData() {
        var proximaSegundaAs10 = OffsetDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        var medico = cadastrarMedico("Medico", "11955500000", "medico@clinica.com", "123456",
                Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");

        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(null, Especialidade.CARDIOLOGIA,
                proximaSegundaAs10);

        assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, OffsetDateTime data) {
        testEntityManager.persist(new Consulta(medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String telefone, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, telefone, email, crm, especialidade));
        testEntityManager.persist(medico);

        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        testEntityManager.persist(paciente);

        return paciente;
    }

    private MedicoInputModel dadosMedico(String nome, String telefone, String email, String crm, Especialidade especialidade) {
        return new MedicoInputModel(
                nome,
                telefone,
                email,
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacienteInputModel dadosPaciente(String nome, String email, String cpf) {
        return new PacienteInputModel(
                nome,
                email,
                cpf,
                dadosEndereco()
        );
    }

    private Endereco dadosEndereco() {
        return new Endereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}