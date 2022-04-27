package com.nelcamacho.serieservice.repository;

import com.nelcamacho.serieservice.models.DbSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbSequenceRepository extends MongoRepository<DbSequence, String> {
}
