package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.ItemSalida;

@Repository
public interface ItemSalidaRepository extends JpaRepository<ItemSalida, Long> {

    List<ItemSalida> findBySalidaProductoId(Long entradaProductoId);

    Page<ItemSalida> findBySalidaProductoId(Long salidaProductoId, Pageable pageable);
}
