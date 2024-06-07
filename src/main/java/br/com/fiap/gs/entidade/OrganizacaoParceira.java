package br.com.fiap.gs.entidade;

import br.com.fiap.gs.dto.CadastrarOrganizacaoParceira;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;


@Table(name = "organizacoes")
@Entity(name = "OrganizacaoParceira")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class OrganizacaoParceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100)
    private String nome;

    @Size(min = 1, max = 100)
    private String contatoInformacao;


    public OrganizacaoParceira(CadastrarOrganizacaoParceira dados) {
        this.nome = dados.nome();
        this.contatoInformacao = dados.contatoInformacao();
    }

    public void atualizarIniciativa(@Valid OrganizacaoParceira dados) {

        if (Objects.nonNull(dados.getNome())) {
            this.nome = dados.getNome();
        }
        if (Objects.nonNull(dados.getContatoInformacao())) {
            this.contatoInformacao = dados.getContatoInformacao();
        }

    }
    public Long id() {
        return id;
    }

}
