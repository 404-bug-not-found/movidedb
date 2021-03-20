package com.galvanize.gmoviedb.MovieDatabase.repository;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

    public Movie findByTitle(String title);
}
