package com.galvanize.gmoviedb.MovieDatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="movie_id")
    private UUID id;

    @Column(name="movie_title")
    private String title;

    @Column(name="movie_release")
    private String release;

    @Column(name="movie_director")
    private String director;

    public Movie(String title, String release, String director) {
        this.title=title;
        this.release=release;
        this.director=director;
    }
}
