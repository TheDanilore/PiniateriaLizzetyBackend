package com.danilore.piniateria_lizzety.repository;

import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.TipoDocumentoIdentidad;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TipoDocumentoIdentidadRepository extends JpaRepository<TipoDocumentoIdentidad, String> {
    Optional<TipoDocumentoIdentidad> findByAbreviatura(String abreviatura);
    Optional<TipoDocumentoIdentidad> findByDescripcion(String descripcion);

}
