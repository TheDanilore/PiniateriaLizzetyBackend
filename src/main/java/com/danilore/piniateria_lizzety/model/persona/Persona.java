package com.danilore.piniateria_lizzety.model.persona;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.persona.enums.GeneroEnum;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String direccion;

    @Column(unique = true)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "tipodocumentoidentidad_id", referencedColumnName = "id", nullable = false)
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id_provincia", nullable = false)
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "distrito_id", referencedColumnName = "id_distrito", nullable = false)
    private Distrito distrito;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroEnum genero;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Persona(Long id, String nombres, String apellidos, String direccion, String telefono,
            TipoDocumentoIdentidad tipoDocumentoIdentidad, String numeroDocumento, Departamento departamento,
            Provincia provincia, Distrito distrito, String correo, LocalDate fechaNacimiento, GeneroEnum genero,
            String lugarNacimiento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
        this.numeroDocumento = numeroDocumento;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.lugarNacimiento = lugarNacimiento;
    }

    public Persona() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
