package br.com.fiap.gs.dto;

import jakarta.validation.constraints.NotNull;

public record CadastrarOrganizacaoParceira(

    @NotNull
    String nome,

    @NotNull
    String contatoInformacao) {
}
