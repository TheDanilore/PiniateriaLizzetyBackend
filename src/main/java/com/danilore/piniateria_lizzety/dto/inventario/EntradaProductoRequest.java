package com.danilore.piniateria_lizzety.dto.inventario;

import java.util.List;

public class EntradaProductoRequest {
    private EntradaProductoDTO entradaProductoDTO;
    private List<ItemEntradaDTO> itemsEntradaDTO;

    public EntradaProductoRequest(EntradaProductoDTO entradaProductoDTO, List<ItemEntradaDTO> itemsEntradaDTO) {
        this.entradaProductoDTO = entradaProductoDTO;
        this.itemsEntradaDTO = itemsEntradaDTO;
    }

    public EntradaProductoRequest() {
    }

    public EntradaProductoDTO getEntradaProductoDTO() {
        return entradaProductoDTO;
    }

    public void setEntradaProductoDTO(EntradaProductoDTO entradaProductoDTO) {
        this.entradaProductoDTO = entradaProductoDTO;
    }

    public List<ItemEntradaDTO> getItemsEntradaDTO() {
        return itemsEntradaDTO;
    }

    public void setItemsEntradaDTO(List<ItemEntradaDTO> itemsEntradaDTO) {
        this.itemsEntradaDTO = itemsEntradaDTO;
    }

    // Getters y Setters
}