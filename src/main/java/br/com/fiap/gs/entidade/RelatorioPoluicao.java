package br.com.fiap.gs.entidade;

import br.com.fiap.gs.dto.CadastrarRelatorio;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Table(name = "relatorios")
@Entity(name = "RelatorioPoluicao")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Getter
@Setter
public class RelatorioPoluicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localidade;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDate dataRelatorio;

    @ManyToOne
    @JoinColumn(name = "iniciativa_id")
    private Iniciativa iniciativa;

    public RelatorioPoluicao(CadastrarRelatorio dados) {
        this.localidade = dados.localidade();
        this.descricao = dados.descricao();
        this.dataRelatorio = LocalDate.now();
    }

    public void atualizarRelatorioPoluicao(RelatorioPoluicao dados) {

        if (Objects.nonNull(dados.getLocalidade())) {
            this.localidade = dados.getLocalidade();
        }
        if (Objects.nonNull(dados.getDescricao())) {
            this.descricao = dados.getDescricao();
        }
        if (Objects.nonNull(dados.getDataRelatorio())) {
            this.dataRelatorio = dados.getDataRelatorio();
        }
    }
    public Long id() {
        return id;
    }

}
