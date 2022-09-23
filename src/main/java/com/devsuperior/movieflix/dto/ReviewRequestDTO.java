package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewRequestDTO {

    @NotBlank
    private String text;

    @NotNull
    private Long movieId;

    public ReviewRequestDTO() {}

    public ReviewRequestDTO(String text, Long movieId) {
        this.text = text;
        this.movieId = movieId;
    }

    public ReviewRequestDTO(Review review) {
        text = review.getText();
        movieId = review.getMovie().getId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
