package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitulo(String titulo);

    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);

    Optional<List<Serie>> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThan(String ator, Double avaliacao);

    Optional<List<Serie>> findTop5ByOrderByAvaliacaoDesc();

    Optional<List<Serie>> findByGenero(Categoria categoriaEncontrada);
//
////   JPQL buscando por numero maximo de temporadas e com avaliação maior ou igual determinado valor
//    Optional<List<Serie>> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer temporadas, Double avaliacao);

//    @Query("select s from Serie s where s.totalTemporadas <= 3 and s.avaliacao > 7.5 order by s.avaliacao desc")
//    Optional<List<Serie>> searchByTemporadasAndAvaliacao();
    @Query("select s from Serie s where s.totalTemporadas <= :totalTemporadas and s.avaliacao > :avaliacao order by s.avaliacao desc")
    Optional<List<Serie>> seriesPorTemporadaEAvaliacao(@Param("totalTemporadas") int totalTemporadas, @Param("avaliacao") double avaliacao);
}
