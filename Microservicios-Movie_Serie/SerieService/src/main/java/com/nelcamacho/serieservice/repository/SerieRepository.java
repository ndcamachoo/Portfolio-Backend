package com.nelcamacho.serieservice.repository;

import com.nelcamacho.serieservice.models.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends MongoRepository<Serie, Long> {

    List<Serie> findAllByGenre(String genre);


}
