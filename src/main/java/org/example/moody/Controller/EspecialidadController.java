package org.example.moody.Controller;

import org.example.moody.Model.Especialidad;
import org.example.moody.Services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialidades")
@Validated
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public List<Especialidad> getAllEspecialidades() {
        return especialidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable Integer id) {
        Optional<Especialidad> especialidad = especialidadService.findById(id);
        return especialidad.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Especialidad createEspecialidad(@Valid @RequestBody Especialidad especialidad) {
        return especialidadService.create(especialidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> updateEspecialidad(@PathVariable Integer id, @Valid @RequestBody Especialidad especialidad) {
        try {
            Especialidad updated = especialidadService.update(id, especialidad);
            return ResponseEntity.ok(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Integer id) {
        try {
            especialidadService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}