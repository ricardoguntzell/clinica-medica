package br.com.guntz.clinica.medica.api.domain.model.medico;

import br.com.guntz.clinica.medica.api.domain.model.endereco.Endereco;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
public class MedicoDetalhadoModel {

    private Long id;

    private String nome;

    private String telefone;

    private String email;

    private String crm;

    private Especialidade especialidade;

    private Endereco endereco;

    private boolean ativo;

    public MedicoDetalhadoModel(Medico medico) {
        BeanUtils.copyProperties(medico, this);
    }
}
