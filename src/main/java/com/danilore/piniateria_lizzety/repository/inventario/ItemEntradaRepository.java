package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.inventario.ItemEntrada;

@Repository
public interface ItemEntradaRepository extends JpaRepository<ItemEntrada, Long>{

    List<ItemEntrada> findByEntradaProductoId(Long entradaProductoId);
    
    Page<ItemEntrada> findByEntradaProductoId(Long entradaProductoId, Pageable pageable);
}
