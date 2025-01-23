package com.danilore.piniateria_lizzety.dto.inventario;

import java.util.List;

public class SalidaProductoRequest {
    private SalidaProductoDTO salidaProductoDTO;
    private List<ItemSalidaDTO> itemsSalidaDTO;

    public SalidaProductoRequest(SalidaProductoDTO salidaProductoDTO, List<ItemSalidaDTO> itemsSalidaDTO) {
        this.salidaProductoDTO = salidaProductoDTO;
        this.itemsSalidaDTO = itemsSalidaDTO;
    }

    public SalidaProductoRequest() {
    }

    public SalidaProductoDTO getSalidaProductoDTO() {
        return salidaProductoDTO;
    }

    public void setSalidaProductoDTO(SalidaProductoDTO salidaProductoDTO) {
        this.salidaProductoDTO = salidaProductoDTO;
    }

    public List<ItemSalidaDTO> getItemsSalidaDTO() {
        return itemsSalidaDTO;
    }

    public void setItemsSalidaDTO(List<ItemSalidaDTO> itemsSalidaDTO) {
        this.itemsSalidaDTO = itemsSalidaDTO;
    }




}