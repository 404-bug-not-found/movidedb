package com.galvanize.gmoviedb.MovieDatabase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviedb")
public class MovieDBController {

    //movie service

    @GetMapping("/list")
    public String getAllMovies(){

        return "[]";

    }
}
