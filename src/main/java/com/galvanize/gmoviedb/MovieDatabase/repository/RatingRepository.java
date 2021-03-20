package com.galvanize.gmoviedb.MovieDatabase.repository;

import com.galvanize.gmoviedb.MovieDatabase.entity.Movie;
import com.galvanize.gmoviedb.MovieDatabase.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {


}

