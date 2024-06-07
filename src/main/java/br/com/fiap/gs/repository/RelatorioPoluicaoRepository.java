package br.com.fiap.gs.repository;

import br.com.fiap.gs.entidade.Iniciativa;
import br.com.fiap.gs.entidade.RelatorioPoluicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RelatorioPoluicaoRepository
    extends JpaRepository<RelatorioPoluicao, Long> {

    // Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<Iniciativa> findByParteNomeContaining(@Param("parteNome") String parteNome);

    // Pesquisar por intervalo de datas (usando data de criação fictícia)
    @Query("SELECT u FROM Usuario u WHERE u.dataCriacao BETWEEN :dataInicio AND :dataFim")
    List<Iniciativa> findByIntervaloDeDatas(@Param("dataInicio") LocalDateTime dataInicio,
                                            @Param("dataFim") LocalDateTime dataFim);

    // Pesquisar utilizando dois parâmetros (nome e descricao)
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.descricao = :descricao")
    List<Iniciativa> findByNomeAndDescricao(@Param("nome") String nome, @Param("descricao") String descricao);
}
