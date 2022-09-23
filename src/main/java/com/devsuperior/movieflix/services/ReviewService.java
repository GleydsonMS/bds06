package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewRequestDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired AuthService authService;

    @PreAuthorize("hasAnyRole('MEMBER')")
    @Transactional
    public ReviewDTO insert(ReviewRequestDTO reviewRequestDTO) {
        User user = authService.authenticated();
        Movie movie = movieRepository.getOne(reviewRequestDTO.getMovieId());
        Review entity = new Review();
        copyDtoToEntity(reviewRequestDTO, entity, movie, user);
        entity = repository.save(entity);
        return new ReviewDTO(entity);
    }

    private void copyDtoToEntity(ReviewRequestDTO reviewRequestDTO, Review entity, Movie movie, User user) {
        entity.setText(reviewRequestDTO.getText());
        entity.setMovie(movie);
        entity.setUser(user);
    }
}
