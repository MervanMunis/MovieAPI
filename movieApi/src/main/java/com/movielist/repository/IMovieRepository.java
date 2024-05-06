package com.movielist.repository;

import com.movielist.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer>{
    
}
