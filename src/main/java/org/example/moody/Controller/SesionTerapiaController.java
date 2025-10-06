package org.example.moody.Controller;

import org.example.moody.Model.SesionTerapia;
import org.example.moody.Services.SesionTerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sesiones-terapia")
@Validated
public class SesionTerapiaController {

    @Autowired
    private SesionTerapiaService sesionTerapiaService;

    @GetMapping
    public List<SesionTerapia> getAllSesiones() {
        return sesionTerapiaService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<SesionTerapia> getSesionById(@PathVariable Integer id) {
        Optional<SesionTerapia> sesion = sesionTerapiaService.findById(id);
        return sesion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/cita/{idCita}")
    public ResponseEntity<SesionTerapia> getSesionByCita(@PathVariable Integer idCita) {
        Optional<SesionTerapia> sesion = sesionTerapiaService.findByCitaId(idCita);
        return sesion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SesionTerapia createSesion(@Valid @RequestBody SesionTerapia sesion) {
        return sesionTerapiaService.create(sesion);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SesionTerapia> updateSesion(@PathVariable Integer id, @Valid @RequestBody SesionTerapia sesionActualizada) {
        try {
            SesionTerapia updated = sesionTerapiaService.update(id, sesionActualizada);
            return ResponseEntity.ok(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Para errores de validación (e.g., cita inexistente)
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable Integer id) {
        try {
            sesionTerapiaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}