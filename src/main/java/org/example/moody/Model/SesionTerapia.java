package org.example.moody.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.example.moody.Model.Cita;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "SesionTerapia")
public class SesionTerapia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSesion")
    private Long idSesion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDcita", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cita cita;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Min(value = 0, message = "La duración debe ser un número positivo")
    @Column(name = "duracionmin")
    private Integer duracionMin;

    public SesionTerapia() {}

    public SesionTerapia(Cita cita, String notas, Integer duracionMin) {
        this.cita = cita;
        this.notas = notas;
        this.duracionMin = duracionMin;
    }


    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
        if (cita != null) {
            cita.setSesionTerapia(this);
        }
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }
}