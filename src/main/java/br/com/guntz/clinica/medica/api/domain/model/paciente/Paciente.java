package br.com.guntz.clinica.medica.api.domain.model.paciente;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    private Integer ativo = 0;

    public Paciente(PacienteInputModel pacienteInputModel) {
        BeanUtils.copyProperties(pacienteInputModel, this);
    }

    public void ativar() {
        if (estaInativo()) {
            System.out.println("kiko");
            setAtivo(1);
        }
    }

    public boolean estaAtivo() {
        return getAtivo().equals(1) ;
    }

    public boolean estaInativo() {
        return !estaAtivo() ;
    }
}
