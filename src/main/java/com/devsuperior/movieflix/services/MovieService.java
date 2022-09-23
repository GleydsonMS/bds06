package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie movie = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Entity not found"));
        return new MovieDTO(movie);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviewsByMovieId(Long id) {
        Movie movie = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Entity not found"));
        return movie.getReviews().stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<MovieGenreDTO> findMoviesByGenreId(Long id, Pageable pageable) {
        Genre genre = (id == 0) ? null : genreRepository.getOne(id);
        Page<Movie> movies =
                (genre == null) ? repository.findAll(PageRequest.of(0, 10, Sort.by("title"))) :
                        repository.findByGenre(genre, pageable);
        return movies.map(MovieGenreDTO::new);
    }
}
