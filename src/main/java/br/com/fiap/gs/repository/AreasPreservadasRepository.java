package br.com.fiap.gs.repository;

import br.com.fiap.gs.entidade.AreasPreservadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AreasPreservadasRepository
        extends JpaRepository<AreasPreservadas, Long> {

    // Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<AreasPreservadas> findByParteNomeContaining(@Param("parteNome") String parteNome);

    // Pesquisar por intervalo de datas (usando data de criação fictícia)
    @Query("SELECT u FROM Usuario u WHERE u.dataCriacao BETWEEN :dataInicio AND :dataFim")
    List<AreasPreservadas> findByIntervaloDeDatas(@Param("dataInicio") LocalDateTime dataInicio,
                                         @Param("dataFim") LocalDateTime dataFim);

    // Pesquisar utilizando dois parâmetros (nome e id)
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.id = :id")
    List<AreasPreservadas> findByNomeAndId(@Param("nome") String nome, @Param("id") int id);
}
