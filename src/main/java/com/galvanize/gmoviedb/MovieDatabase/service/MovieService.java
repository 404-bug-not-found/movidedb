package com.galvanize.gmoviedb.MovieDatabase.service;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import com.galvanize.gmoviedb.MovieDatabase.entity.Rating;
import com.galvanize.gmoviedb.MovieDatabase.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public ResponseEntity<Movie> createMovie(String title, String release, String director) {
        Movie movie=new Movie(title,release,director);
        Movie savedMovie= movieRepository.save(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);

    }

    public ResponseEntity<List<Movie>> getAllMovies() {

        return new ResponseEntity<List<Movie>> (movieRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> getMovie(String title) {

        Movie movie = movieRepository.findByTitle(title);
        if (movie==null) {
            return new ResponseEntity<String> ("{\"message\":\"Movie Does Not Exists\"}}",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Movie> (movie,HttpStatus.OK);
    }

    public ResponseEntity<?> getAverageRating(String title) {
        Movie movie = movieRepository.findByTitle(title);
        Rating ratingEntity = new Rating();
        Double averageRating= Math.ceil(movie.getRatingList().stream().mapToDouble(x->x.getRating()).average().getAsDouble());
        return new ResponseEntity<Double> (averageRating, HttpStatus.OK);

    }

}
