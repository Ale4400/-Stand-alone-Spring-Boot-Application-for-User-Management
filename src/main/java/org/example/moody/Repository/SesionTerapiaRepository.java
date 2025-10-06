package org.example.moody.Repository;

import org.example.moody.Model.SesionTerapia;
import org.example.moody.Model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SesionTerapiaRepository extends JpaRepository<SesionTerapia, Integer> {

    Optional<SesionTerapia> findByCitaIdCita(Integer idCita);

    List<SesionTerapia> findByNotasIsNotNullAndNotasNot(String notas);

    List<SesionTerapia> findByDuracionMinGreaterThan(Integer duracionMin);

    @Query("SELECT st FROM SesionTerapia st JOIN st.cita c WHERE st.notas LIKE %:palabraClave% AND c.estado = 'COMPLETADA'")
    List<SesionTerapia> findSesionesCompletadasConNotasConteniendo(@Param("palabraClave") String palabraClave);

    void deleteByCitaIdCita(Integer idCita);
}