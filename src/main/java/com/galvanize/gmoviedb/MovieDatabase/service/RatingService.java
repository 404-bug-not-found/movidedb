package com.galvanize.gmoviedb.MovieDatabase.service;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import com.galvanize.gmoviedb.MovieDatabase.entity.Rating;
import com.galvanize.gmoviedb.MovieDatabase.repository.MovieRepository;
import com.galvanize.gmoviedb.MovieDatabase.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingRepository ratingRepository;

    public ResponseEntity<Rating> setMovieRating(String title, String rating) {

        Movie movie = movieRepository.findByTitle(title);
        Rating ratingEntity = new Rating();
        ratingEntity.setRating(Long.valueOf(rating));
        ratingEntity.setMovie(movie);


        Rating savedRating = ratingRepository.save(ratingEntity);

        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);

    }

    public ResponseEntity<Rating> setMovieReviewAndRating(String title, String rating, String review) {

        Movie movie = movieRepository.findByTitle(title);
        Rating ratingEntity = new Rating();
        ratingEntity.setRating(Long.valueOf(rating));
        ratingEntity.setReview(review);
        ratingEntity.setMovie(movie);

        Rating savedRating = ratingRepository.save(ratingEntity);

        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }
}
