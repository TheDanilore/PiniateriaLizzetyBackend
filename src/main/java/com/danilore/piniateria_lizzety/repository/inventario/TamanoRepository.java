package com.danilore.piniateria_lizzety.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.inventario.Tamano;

@Repository
public interface TamanoRepository extends JpaRepository<Tamano, Long> {

}
