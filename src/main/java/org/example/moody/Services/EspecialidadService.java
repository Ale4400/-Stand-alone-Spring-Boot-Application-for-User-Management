package org.example.moody.Services;

import org.example.moody.Model.Especialidad;
import org.example.moody.Repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public Especialidad create(Especialidad especialidad) {
        if (especialidadRepository.existsByNombre(especialidad.getNombre())) {
            throw new IllegalArgumentException("La especialidad ya existe");
        }
        return especialidadRepository.save(especialidad);
    }

    public List<Especialidad> findAll() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> findById(Integer id) {
        return especialidadRepository.findById(id);
    }

    public Especialidad update(Integer id, Especialidad especialidadActualizada) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada con ID: " + id));

        if (!especialidad.getIdEspecialidad().equals(especialidadActualizada.getIdEspecialidad()) ||
                especialidadRepository.existsByNombre(especialidadActualizada.getNombre()) &&
                        !especialidad.getNombre().equals(especialidadActualizada.getNombre())) {
            throw new IllegalArgumentException("Nombre de especialidad inválido o duplicado");
        }

        especialidad.setNombre(especialidadActualizada.getNombre());
        return especialidadRepository.save(especialidad);
    }

    public void deleteById(Integer id) {
        if (!especialidadRepository.existsById(id)) {
            throw new EntityNotFoundException("Especialidad no encontrada con ID: " + id);
        }
        especialidadRepository.deleteById(id);
    }
}