package com.nelcamacho.movieservice.repository;

import com.nelcamacho.movieservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByGenre(String genre);
}
