package br.com.lima.screenmatch.repository;

import br.com.lima.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
