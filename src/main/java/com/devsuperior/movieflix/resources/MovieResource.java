package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movieDTO = service.findById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsByMovieId(@PathVariable Long id) {
        List<ReviewDTO> reviewsDTO = service.findReviewsByMovieId(id);
        return ResponseEntity.ok().body(reviewsDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MovieGenreDTO>> findMoviesByGenreId(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable
    ) {
        Page<MovieGenreDTO> movieGenreDTO = service.findMoviesByGenreId(genreId, pageable);
        return ResponseEntity.ok().body(movieGenreDTO);
    }
}
