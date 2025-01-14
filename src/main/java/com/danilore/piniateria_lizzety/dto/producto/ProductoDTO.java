package com.danilore.piniateria_lizzety.dto.producto;

import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.producto.Producto;

public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private CategoriaProductoDTO categoriaProducto;
    private UnidadMedidaDTO unidadMedida;
    private ProveedorDTO proveedor;
    private UbicacionDTO ubicacion;
    private EstadoEnum estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductoDTO(Long id, String nombre, String descripcion, CategoriaProductoDTO categoriaProducto,
            UnidadMedidaDTO unidadMedida, ProveedorDTO proveedor, UbicacionDTO ubicacion, EstadoEnum estado,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaProducto = categoriaProducto;
        this.unidadMedida = unidadMedida;
        this.proveedor = proveedor;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductoDTO() {
    }

    public static ProductoDTO fromEntity(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());

        // Convertir CategoriaProducto a DTO
        if (producto.getCategoriaProducto() != null) {
            dto.setCategoriaProducto(CategoriaProductoDTO.fromEntity(producto.getCategoriaProducto()));
        }

        if (producto.getUnidadMedida() != null) {
            dto.setUnidadMedida(UnidadMedidaDTO.fromEntity(producto.getUnidadMedida()));
        }

        if (producto.getProveedor() != null) {
            dto.setProveedor(ProveedorDTO.fromEntity(producto.getProveedor()));
        }

        if (producto.getUbicacion() != null) {
            dto.setUbicacion(UbicacionDTO.fromEntity(producto.getUbicacion()));
        }

        dto.setEstado(producto.getEstado());
        dto.setCreatedAt(producto.getCreatedAt());
        dto.setUpdatedAt(producto.getUpdatedAt());
        return dto;
    }

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setId(this.id);
        producto.setDescripcion(this.descripcion);
        producto.setCreatedAt(this.createdAt);
        producto.setUpdatedAt(this.updatedAt);
        return producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProductoDTO getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProductoDTO categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public UnidadMedidaDTO getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaDTO unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
