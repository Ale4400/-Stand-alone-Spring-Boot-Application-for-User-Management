package org.example.moody.Repository;

import org.example.moody.Model.Cita;
import org.example.moody.Model.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByIdUsuario(Integer idUsuario);

    List<Cita> findByIdProfesional(Integer idProfesional);

    List<Cita> findByEstado(EstadoCita estado);

    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Cita> findByEstadoAndIdProfesional(EstadoCita estado, Integer idProfesional);

    @Query("SELECT c FROM Cita c JOIN FETCH c.sesionTerapia st WHERE c.estado = :estado AND st.duracionMin > :duracionMin")
    List<Cita> findCitasCompletadasConDuracion(@Param("estado") EstadoCita estado, @Param("duracionMin") Integer duracionMin);

    long countByEstado(EstadoCita estado);

    List<Cita> findByEstadoAndFechaHoraBetween(EstadoCita estado, LocalDateTime inicio, LocalDateTime fin);
}