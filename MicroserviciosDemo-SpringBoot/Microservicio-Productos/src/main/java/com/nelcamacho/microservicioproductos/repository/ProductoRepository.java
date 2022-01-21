package com.nelcamacho.microservicioproductos.repository;

import com.nelcamacho.microserviciocommons.models.Producto; // Utilizando el modelo de producto de commons
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
