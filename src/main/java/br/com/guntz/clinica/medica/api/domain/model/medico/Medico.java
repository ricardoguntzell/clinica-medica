package br.com.guntz.clinica.medica.api.domain.model.medico;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(MedicoInputModel medicoInputModel) {
        BeanUtils.copyProperties(medicoInputModel, this);
        ativar();
    }

    public void atualizar(MedicoResumoInputModel medicoAtualizado) {
        if (medicoAtualizado.nome() != null && !medicoAtualizado.nome().isEmpty()){
            setNome(medicoAtualizado.nome());
        }

        if (medicoAtualizado.telefone() != null){
            setTelefone(medicoAtualizado.telefone());
        }
    }
    public void ativar() {
        if (estaAtivo()) {
            throw new NegocioException("Médico já está ativo");
        }

        setAtivo(true);
    }

    public void inativar() {
        if (estaInativo()) {
            throw new NegocioException("Médico já está inativo");
        }
        setAtivo(false);
    }

    private boolean estaAtivo() {
        return isAtivo();
    }

    private boolean estaInativo() {
        return !estaAtivo();
    }
}
