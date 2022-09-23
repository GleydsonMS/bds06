package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewRequestDTO;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewResource {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewDTO dto =service.insert(reviewRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
