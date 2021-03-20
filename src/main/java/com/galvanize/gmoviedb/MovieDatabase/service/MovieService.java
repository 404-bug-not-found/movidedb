package com.galvanize.gmoviedb.MovieDatabase.service;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import com.galvanize.gmoviedb.MovieDatabase.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public ResponseEntity<Movie> createMovie(String title, String release, String director) {
        Movie movie=new Movie(title,release,director);
        Movie savedMovie= movieRepository.save(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);

    }
}
