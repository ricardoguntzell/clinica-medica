package br.com.guntz.clinica.medica.api.domain.model.medico;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import br.com.guntz.clinica.medica.api.model.input.MedicoInputModel;
import br.com.guntz.clinica.medica.api.model.input.MedicoResumoInputModel;
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

    private Integer ativo;

    public Medico(MedicoInputModel medicoInputModel) {
        BeanUtils.copyProperties(medicoInputModel, this);
    }

    public void atualizar(MedicoResumoInputModel medicoResumoInputModel) {
        BeanUtils.copyProperties(medicoResumoInputModel, this);
    }

    public void excluir() {
        setAtivo(0);
    }
}
