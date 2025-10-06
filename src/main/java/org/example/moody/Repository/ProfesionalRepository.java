package org.example.moody.Repository;

import org.example.moody.Model.Especialidad;
import org.example.moody.Model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

    Optional<Profesional> findByEmail(String email);

    List<Profesional> findByIdEspecialidad(Integer idEspecialidad);

    List<Profesional> findByEspecialidad(Especialidad especialidad);

    boolean existsByEmail(String email);


    List<Profesional> findByNombreContainingIgnoreCase(String nombreParcial);
}