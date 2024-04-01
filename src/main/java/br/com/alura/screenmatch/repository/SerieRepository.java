package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Collection<Object> findByTitulo(String titulo);
}
