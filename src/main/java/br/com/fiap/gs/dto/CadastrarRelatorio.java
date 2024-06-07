package br.com.fiap.gs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CadastrarRelatorio(

        @NotBlank
        String localidade,

        @NotBlank
        String descricao,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate dataRelatorio
) {
}
