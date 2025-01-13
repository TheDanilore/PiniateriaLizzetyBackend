package com.danilore.piniateria_lizzety.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.persona.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    Page<Distrito> findByProvinciaIdProvincia(Integer id, Pageable pageable);
    
    Optional<Distrito> findByDescripcion(String descripcion);


}
