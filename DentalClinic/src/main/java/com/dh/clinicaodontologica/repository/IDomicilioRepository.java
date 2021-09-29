package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {
}
