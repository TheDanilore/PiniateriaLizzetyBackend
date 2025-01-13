package com.danilore.piniateria_lizzety.repository;

import com.danilore.piniateria_lizzety.model.Persona;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Métodos personalizados
    // Busca una persona por su correo, número de documento o teléfono
    Optional<Persona> findByCorreoOrNumeroDocumentoOrTelefono(String correo, String numeroDocumento, String telefono);

    Optional<Persona> findByCorreo(String correo); // Busca una persona por su correo

    Optional<Persona> findByTelefono(String telefono); // Busca una persona por su teléfono

    List<Persona> findByNombres(String nombres); // Busca una persona por su nombre

    List<Persona> findByApellidos(String apellidos); // Busca una persona por su apellido

    List<Persona> findByNombresAndApellidos(String nombres, String apellidos); // Busca una persona por su nombre y
                                                                               // apellido

    List<Persona> findByNombresContaining(String fragmento); // Busca una persona por un fragmento de su nombre

    List<Persona> findByApellidosContaining(String fragmento); // Busca una persona por un fragmento de su apellido

    @Query("SELECT p FROM Persona p JOIN FETCH p.tipoDocumentoIdentidad WHERE p.id = :id")
    Optional<Persona> findByIdConTipoDocumento(@Param("id") Long id);

}
