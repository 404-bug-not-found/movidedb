package com.galvanize.gmoviedb.MovieDatabase.controller;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import com.galvanize.gmoviedb.MovieDatabase.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moviedb")
public class MovieDBController {


    @Autowired
    MovieService movieService;

    @GetMapping("/list")
    public String getAllMovies(){

        return "[]";

    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> postMovie(@RequestParam String title, @RequestParam String release, @RequestParam String director){
     return movieService.createMovie(title,release,director);
    }
}
