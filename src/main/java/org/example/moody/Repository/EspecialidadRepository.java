package org.example.moody.Repository;

import org.example.moody.Model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    Optional<Especialidad> findByNombre(String nombre);

    List<Especialidad> findByNombreContainingIgnoreCase(String nombreParcial);

    boolean existsByNombre(String nombre);
}