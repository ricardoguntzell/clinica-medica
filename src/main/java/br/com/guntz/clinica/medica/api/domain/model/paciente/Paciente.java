package br.com.guntz.clinica.medica.api.domain.model.paciente;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Paciente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(PacienteInputModel pacienteEntrada) {
        BeanUtils.copyProperties(pacienteEntrada, this);
    }

    public Paciente(PacienteResumoInputModel pacienteEntrada) {
        BeanUtils.copyProperties(pacienteEntrada, this);
        ativar();
    }

    public void ativar() {
        if (estaAtivo()) {
            throw new NegocioException("Paciente já está ativo");
        }

        setAtivo(true);
    }

    public void inativar() {
        if (estaInativo()) {
            throw new NegocioException("Paciente já está inativo");
        }
        setAtivo(false);
    }

    public void atualizar(PacienteResumoInputModel pacienteAtualizado) {
        if (pacienteAtualizado.nome() != null && !pacienteAtualizado.nome().isEmpty()) {
            setNome(pacienteAtualizado.nome());
        }

        if (pacienteAtualizado.endereco() != null) {
            setEndereco(pacienteAtualizado.endereco());
        }
    }

    private boolean estaAtivo() {
        return isAtivo();
    }

    private boolean estaInativo() {
        return !estaAtivo();
    }

}
