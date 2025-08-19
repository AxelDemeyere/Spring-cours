package org.app.exo2.movie.repository;

import org.app.exo2.director.model.entity.Director;
import org.app.exo2.movie.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findMoviesByDirector(Director director);
}
