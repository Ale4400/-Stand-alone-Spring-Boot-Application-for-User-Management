package org.example.moody.Controller;

import org.example.moody.Model.Cita;
import org.example.moody.Services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@Validated
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Integer id) {
        Optional<Cita> cita = citaService.findById(id);
        return cita.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/usuario/{idUsuario}")
    public List<Cita> getCitasByUsuario(@PathVariable Integer idUsuario) {
        return citaService.findByIdUsuario(idUsuario);
    }

    @PostMapping
    public Cita createCita(@Valid @RequestBody Cita cita) {
        return citaService.create(cita);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Integer id, @Valid @RequestBody Cita citaActualizada) {
        try {
            Cita updated = citaService.update(id, citaActualizada);
            return ResponseEntity.ok(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Para errores de validación FK
        }
    }

    // Cambiado a Long
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Integer id) {
        try {
            citaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}