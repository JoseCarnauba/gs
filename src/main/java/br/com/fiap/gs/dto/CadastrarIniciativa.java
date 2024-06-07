package br.com.fiap.gs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CadastrarIniciativa(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate dataIniciativa ) {
}
