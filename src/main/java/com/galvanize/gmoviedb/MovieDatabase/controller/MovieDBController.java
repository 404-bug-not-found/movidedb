package com.galvanize.gmoviedb.MovieDatabase.controller;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import com.galvanize.gmoviedb.MovieDatabase.entity.Rating;
import com.galvanize.gmoviedb.MovieDatabase.service.MovieService;
import com.galvanize.gmoviedb.MovieDatabase.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviedb")
public class MovieDBController {


    @Autowired
    MovieService movieService;

    @Autowired
    RatingService ratingService;

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> getAllMovies(){

        //return "[]";

        return movieService.getAllMovies();

    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> postMovie(@RequestParam String title, @RequestParam String release, @RequestParam String director){
     return movieService.createMovie(title,release,director);
    }

    @GetMapping("/movie")
    public ResponseEntity<?> getMovie(@RequestParam String title){
        return movieService.getMovie(title);
    }

    //"/moviedb/movie/rating"

    @PostMapping("/movie/rating")
    public ResponseEntity<Rating> postMovieRating(@RequestParam String title, @RequestParam String rating){
        return ratingService.setMovieRating(title,rating);
    }

    @GetMapping("movie/averagerating")
    public ResponseEntity<?> getMovieAverageRating(@RequestParam String title){
        return movieService.getAverageRating(title);

    }

    @PostMapping("/movie/review")
    public ResponseEntity<Rating> postMovieReviewAndRating(
            @RequestParam String title, @RequestParam String rating, @RequestParam String review){
        return ratingService.setMovieReviewAndRating(title,rating, review);
    }

}
