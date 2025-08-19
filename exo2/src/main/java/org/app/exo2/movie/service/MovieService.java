package org.app.exo2.movie.service;

import org.app.exo2.director.model.entity.Director;
import org.app.exo2.director.repository.DirectorRepository;
import org.app.exo2.movie.model.dto.MovieRequestDTO;
import org.app.exo2.movie.model.dto.MovieResponseDTO;
import org.app.exo2.movie.model.entity.Movie;
import org.app.exo2.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    public List<MovieResponseDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(Movie::toDTO)
                .toList();
    }

    public MovieResponseDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé avec l'id: " + id));
        return movie.toDTO();
    }

    public MovieResponseDTO createMovie(MovieRequestDTO requestDTO) {
        Movie movie = requestDTO.toEntity();

        if (requestDTO.getDirectorId() != null) {
            Director director = directorRepository.findById(requestDTO.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé avec l'id: " + requestDTO.getDirectorId()));
            movie.setDirector(director);
        }

        return movieRepository.save(movie).toDTO();
    }

    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO requestDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé avec l'id: " + id));

        movie.setTitle(requestDTO.getTitle());
        movie.setDescription(requestDTO.getDescription());
        movie.setReleaseDate(requestDTO.getReleaseDate());
        movie.setDuration(requestDTO.getDuration());
        movie.setGenre(requestDTO.getGenre());

        if (requestDTO.getDirectorId() != null) {
            Director director = directorRepository.findById(requestDTO.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé avec l'id: " + requestDTO.getDirectorId()));
            movie.setDirector(director);
        }

        return movieRepository.save(movie).toDTO();
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Film non trouvé avec l'id: " + id);
        }
        movieRepository.deleteById(id);
    }

    public List<MovieResponseDTO> getMoviesByDirectorId(Long directorId) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé avec l'id: " + directorId));
        return movieRepository.findMoviesByDirector(director).stream()
                .map(Movie::toDTO)
                .toList();
    }
}