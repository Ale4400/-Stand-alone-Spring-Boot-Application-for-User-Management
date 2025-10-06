package org.example.moody.Services;

import org.example.moody.Model.SesionTerapia;
import org.example.moody.Model.Cita;
import org.example.moody.Repository.CitaRepository;
import org.example.moody.Repository.SesionTerapiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SesionTerapiaService {

    @Autowired
    private SesionTerapiaRepository sesionTerapiaRepository;

    @Autowired
    private CitaRepository citaRepository;

    public SesionTerapia create(SesionTerapia sesionTerapia) {
        if (sesionTerapia.getCita() == null || sesionTerapia.getCita().getIdCita() == null) {
            throw new IllegalArgumentException("Cita es requerida");
        }
        Integer idCita = sesionTerapia.getCita().getIdCita();
        if (!citaRepository.existsById(idCita)) {
            throw new IllegalArgumentException("Cita no existe con ID: " + idCita);
        }
        if (sesionTerapiaRepository.findByCitaIdCita(idCita).isPresent()) {
            throw new IllegalArgumentException("Ya existe una sesión para esta cita");
        }

        sesionTerapia.getCita().setSesionTerapia(sesionTerapia);
        return sesionTerapiaRepository.save(sesionTerapia);
    }

    public List<SesionTerapia> findAll() {
        return sesionTerapiaRepository.findAll();
    }


    public Optional<SesionTerapia> findById(Integer id) {
        return sesionTerapiaRepository.findById(id);
    }


    public Optional<SesionTerapia> findByCitaId(Integer idCita) {
        return sesionTerapiaRepository.findByCitaIdCita(idCita);
    }


    public SesionTerapia update(Integer id, SesionTerapia sesionActualizada) {
        SesionTerapia sesion = sesionTerapiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada con ID: " + id));

        if (sesionActualizada.getCita() != null && sesionActualizada.getCita().getIdCita() != null) {
            Integer nuevoIdCita = sesionActualizada.getCita().getIdCita();
            if (!citaRepository.existsById(nuevoIdCita)) {
                throw new IllegalArgumentException("Cita no existe con ID: " + nuevoIdCita);
            }

            if (!nuevoIdCita.equals(sesion.getCita().getIdCita()) &&
                    sesionTerapiaRepository.findByCitaIdCita(nuevoIdCita).isPresent()) {
                throw new IllegalArgumentException("Ya existe una sesión para la nueva cita");
            }
            sesion.setCita(sesionActualizada.getCita());
        }

        if (sesionActualizada.getNotas() != null) {
            sesion.setNotas(sesionActualizada.getNotas());
        }
        if (sesionActualizada.getDuracionMin() != null) {
            sesion.setDuracionMin(sesionActualizada.getDuracionMin());
        }
        return sesionTerapiaRepository.save(sesion);
    }


    public void deleteById(Integer id) {
        if (!sesionTerapiaRepository.existsById(id)) {
            throw new EntityNotFoundException("Sesión no encontrada con ID: " + id);
        }
        sesionTerapiaRepository.deleteById(id);
    }


    public void deleteByCitaId(Integer idCita) {
        if (!citaRepository.existsById(idCita)) {
            throw new EntityNotFoundException("Cita no encontrada con ID: " + idCita);
        }
        sesionTerapiaRepository.deleteByCitaIdCita(idCita);
    }
}