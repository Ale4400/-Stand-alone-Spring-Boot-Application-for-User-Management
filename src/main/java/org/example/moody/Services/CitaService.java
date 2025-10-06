package org.example.moody.Services;

import org.example.moody.Model.Cita;
import org.example.moody.Model.EstadoCita;
import org.example.moody.Repository.CitaRepository;
import org.example.moody.Repository.ProfesionalRepository;
import org.example.moody.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProfesionalRepository profesionalRepository;

    public Cita create(Cita cita) {
        if (cita.getIdUsuario() == null || !usuarioRepository.existsById(cita.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe");
        }
        if (cita.getIdProfesional() == null || !profesionalRepository.existsById(cita.getIdProfesional())) {
            throw new IllegalArgumentException("Profesional no existe");
        }
        if (cita.getFechaHora() == null) {
            throw new IllegalArgumentException("Fecha y hora son requeridas");
        }
        cita.setEstado(EstadoCita.PENDIENTE);
        return citaRepository.save(cita);
    }

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Optional<Cita> findById(Integer id) {
        return citaRepository.findById(id);
    }

    public List<Cita> findByIdUsuario(Integer idUsuario) {
        return citaRepository.findByIdUsuario(idUsuario);
    }

    public List<Cita> findByIdProfesional(Integer idProfesional) {
        return citaRepository.findByIdProfesional(idProfesional);
    }

    public List<Cita> findByEstadoAndFechaHoraBetween(EstadoCita estado, LocalDateTime inicio, LocalDateTime fin) {
        return citaRepository.findByEstadoAndFechaHoraBetween(estado, inicio, fin);
    }

    public Cita update(Integer id, Cita citaActualizada) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con ID: " + id));

        if (citaActualizada.getIdUsuario() != null && !usuarioRepository.existsById(citaActualizada.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no existe");
        }
        if (citaActualizada.getIdProfesional() != null && !profesionalRepository.existsById(citaActualizada.getIdProfesional())) {
            throw new IllegalArgumentException("Profesional no existe");
        }

        if (citaActualizada.getIdUsuario() != null) {
            cita.setIdUsuario(citaActualizada.getIdUsuario());
        }
        if (citaActualizada.getIdProfesional() != null) {
            cita.setIdProfesional(citaActualizada.getIdProfesional());
        }
        if (citaActualizada.getFechaHora() != null) {
            cita.setFechaHora(citaActualizada.getFechaHora());
        }
        if (citaActualizada.getEstado() != null) {
            cita.setEstado(citaActualizada.getEstado());
        }
        return citaRepository.save(cita);
    }

    public void deleteById(Integer id) {
        if (!citaRepository.existsById(id)) {
            throw new EntityNotFoundException("Cita no encontrada con ID: " + id);
        }
        citaRepository.deleteById(id);
    }
}