package com.nelcamacho.movieservice.service;

import com.nelcamacho.movieservice.message.MessageSender;
import com.nelcamacho.movieservice.models.Movie;
import com.nelcamacho.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IEntityService<Movie> {

    /* ======================= Attributes ======================= */

    private final MovieRepository movieRepository;
    private final MessageSender messageSender;

    /* ======================= Methods ======================= */

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie save(Movie entity) {
        Movie mv = movieRepository.save(entity);
        messageSender.send(mv);
        return movieRepository.save(mv);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getListByGenre(String genre) {
        return movieRepository.findAllByGenre(genre);
    }

    /* ======================= Constructors ======================= */

    @Autowired
    public MovieService(MovieRepository movieRepository, MessageSender messageSender) {
        this.movieRepository = movieRepository;
        this.messageSender = messageSender;
    }
}
