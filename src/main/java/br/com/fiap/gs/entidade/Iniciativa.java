package br.com.fiap.gs.entidade;

import br.com.fiap.gs.dto.CadastrarIniciativa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;


@Table(name = "iniciativas")
@Entity(name = "Iniciativa")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Iniciativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 100)
    private String nome;
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataIniciativa;

    public Iniciativa(CadastrarIniciativa dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.dataIniciativa = LocalDate.now();
    }

    public void atualizarIniciativa(Iniciativa dados) {

        if (Objects.nonNull(dados.getNome())) {
            this.nome = dados.getNome();
        }
        if (Objects.nonNull(dados.getDescricao())) {
            this.descricao = dados.getDescricao();
        }
        if (Objects.nonNull(dados.getDataIniciativa())) {
            this.dataIniciativa = dados.getDataIniciativa();
        }
    }
    public Long id() {
        return id;
    }
}

