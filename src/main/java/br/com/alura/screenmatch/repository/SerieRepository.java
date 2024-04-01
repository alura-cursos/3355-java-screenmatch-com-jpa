package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitulo(String titulo);

    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);

    Optional<List<Serie>> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThan(String ator, Double avaliacao);
}
