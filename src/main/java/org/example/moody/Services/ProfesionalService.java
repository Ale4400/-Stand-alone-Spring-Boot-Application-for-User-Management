package org.example.moody.Services;

import org.example.moody.Model.Especialidad;
import org.example.moody.Model.Profesional;
import org.example.moody.Repository.EspecialidadRepository;
import org.example.moody.Repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public Profesional create(Profesional profesional) {
        if (profesionalRepository.existsByEmail(profesional.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (!especialidadRepository.existsById(profesional.getIdEspecialidad())) {
            throw new IllegalArgumentException("Especialidad no existe");
        }
        return profesionalRepository.save(profesional);
    }

    public List<Profesional> findAll() {
        return profesionalRepository.findAll();
    }

    public Optional<Profesional> findById(Integer id) {
        return profesionalRepository.findById(id);
    }

    public List<Profesional> findByEspecialidadId(Integer idEspecialidad) {
        return profesionalRepository.findByIdEspecialidad(idEspecialidad);
    }

    public Profesional update(Integer id, Profesional profesionalActualizado) {
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado con ID: " + id));

        if (!profesional.getIdProfesional().equals(profesionalActualizado.getIdProfesional()) ||
                profesionalRepository.existsByEmail(profesionalActualizado.getEmail()) &&
                        !profesional.getEmail().equals(profesionalActualizado.getEmail())) {
            throw new IllegalArgumentException("Email inválido o duplicado");
        }

        if (!especialidadRepository.existsById(profesionalActualizado.getIdEspecialidad())) {
            throw new IllegalArgumentException("Especialidad no existe");
        }

        profesional.setNombre(profesionalActualizado.getNombre());
        profesional.setIdEspecialidad(profesionalActualizado.getIdEspecialidad());
        profesional.setEmail(profesionalActualizado.getEmail());
        profesional.setTelefono(profesionalActualizado.getTelefono());
        return profesionalRepository.save(profesional);
    }

    public void deleteById(Integer id) {
        if (!profesionalRepository.existsById(id)) {
            throw new EntityNotFoundException("Profesional no encontrado con ID: " + id);
        }
        profesionalRepository.deleteById(id);
    }
}