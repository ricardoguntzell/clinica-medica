package br.com.guntz.clinica.medica.api.controller;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import br.com.guntz.clinica.medica.api.domain.model.paciente.Paciente;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteInputModel;
import br.com.guntz.clinica.medica.api.domain.model.paciente.PacienteModel;
import br.com.guntz.clinica.medica.api.domain.service.PacienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PacienteInputModel> dadosPacienteEntradaJson;

    @WithMockUser
    @Test
    @DisplayName("Deveria devolver código HTTP 400, quando as informações estão inválidas")
    void salvar_cenario1() throws Exception {
        var response = mockMvc.perform(post("/api/pacientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @WithMockUser
    @Test
    @DisplayName("Deveria devolver código HTTP 201, quando as informações estão corretas para a inclusão")
    void salvar_cenario2() throws Exception {
        var response = mockMvc.perform(post("/api/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosPacienteEntradaJson.write(
                                dadosPaciente()
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


    private PacienteInputModel dadosPaciente() {
        return new PacienteInputModel(
                "Regina Luna Eliane Nogueira",
                "regina_nogueira@guntz.com.br",
                "31085353079",
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