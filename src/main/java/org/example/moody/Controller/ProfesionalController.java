package org.example.moody.Controller;

import org.example.moody.Model.Profesional;
import org.example.moody.Services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesionales")
@Validated
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping
    public List<Profesional> getAllProfesionales() {
        return profesionalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesional> getProfesionalById(@PathVariable Integer id) {
        Optional<Profesional> profesional = profesionalService.findById(id);
        return profesional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidad/{idEspecialidad}")
    public List<Profesional> getProfesionalesByEspecialidad(@PathVariable Integer idEspecialidad) {
        return profesionalService.findByEspecialidadId(idEspecialidad);
    }

    @PostMapping
    public Profesional createProfesional(@Valid @RequestBody Profesional profesional) {
        return profesionalService.create(profesional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesional> updateProfesional(@PathVariable Integer id, @Valid @RequestBody Profesional profesionalActualizado) {
        try {
            Profesional updated = profesionalService.update(id, profesionalActualizado);
            return ResponseEntity.ok(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesional(@PathVariable Integer id) {
        try {
            profesionalService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}