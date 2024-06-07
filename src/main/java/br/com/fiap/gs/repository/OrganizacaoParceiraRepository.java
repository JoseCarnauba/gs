package br.com.fiap.gs.repository;

import br.com.fiap.gs.entidade.OrganizacaoParceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrganizacaoParceiraRepository
        extends JpaRepository<OrganizacaoParceira, Long> {

    // Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<OrganizacaoParceira> findByParteNomeContaining(@Param("parteNome") String parteNome);

    // Pesquisar por intervalo de datas (usando data de criação fictícia)
    @Query("SELECT u FROM Usuario u WHERE u.dataCriacao BETWEEN :dataInicio AND :dataFim")
    List<OrganizacaoParceira> findByIntervaloDeDatas(@Param("dataInicio") LocalDateTime dataInicio,
                                         @Param("dataFim") LocalDateTime dataFim);

    // Pesquisar utilizando dois parâmetros (nome e contato)
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.contatoInformacao = :contatoInformacao")
    List<OrganizacaoParceira> findBycontatoInformacao(@Param("nome") String nome, @Param("contatoInformacao") String contatoInformacao);

}
