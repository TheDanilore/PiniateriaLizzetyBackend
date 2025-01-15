package com.danilore.piniateria_lizzety.repository.persona;

import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.persona.TipoDocumentoIdentidad;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TipoDocumentoIdentidadRepository extends JpaRepository<TipoDocumentoIdentidad, String> {
    @Query("SELECT DISTINCT t FROM TipoDocumentoIdentidad t WHERE " +
            "LOWER(t.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(t.idDepartamento AS string) LIKE :criterio")
    Page<TipoDocumentoIdentidad> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<TipoDocumentoIdentidad> findByAbreviatura(String abreviatura);

    Optional<TipoDocumentoIdentidad> findByDescripcion(String descripcion);

}
