package br.com.fiap.gs.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "areas_preservadas")
@Entity(name = "AreasPreservadas")
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class AreasPreservadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String Local;

    @NotNull
    private Boolean PrecisaDeLimpeza;

    @ManyToOne
    @JoinColumn(name = "iniciativa_id")
    private Iniciativa iniciativa;

}
