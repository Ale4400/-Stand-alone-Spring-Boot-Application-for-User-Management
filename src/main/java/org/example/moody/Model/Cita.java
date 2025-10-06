package org.example.moody.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCita")
    private Integer idCita;

    @NotNull(message = "El ID del usuario es requerido")
    @Column(name = "IDusuario", nullable = false)
    private Integer idUsuario;

    @NotNull(message = "El ID del profesional es requerido")
    @Column(name = "IDprofesional", nullable = false)
    private Integer idProfesional;

    @NotNull(message = "La fecha y hora son requeridas")
    @Column(name = "Fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false, columnDefinition = "ENUM('Pendiente', 'Completada', 'Cancelada') DEFAULT 'Pendiente'")
    private EstadoCita estado = EstadoCita.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDusuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDprofesional", insertable = false, updatable = false)
    private Profesional profesional;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private SesionTerapia sesionTerapia;

    public Cita() {}

    public Cita(Integer idUsuario, Integer idProfesional, LocalDateTime fechaHora) {
        this.idUsuario = idUsuario;
        this.idProfesional = idProfesional;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PENDIENTE;
    }


    public Integer getIdCita() { return idCita; }
    public void setIdCita(Integer idCita) { this.idCita = idCita; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdProfesional() { return idProfesional; }
    public void setIdProfesional(Integer idProfesional) { this.idProfesional = idProfesional; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Profesional getProfesional() { return profesional; }
    public void setProfesional(Profesional profesional) { this.profesional = profesional; }

    public SesionTerapia getSesionTerapia() { return sesionTerapia; }
    public void setSesionTerapia(SesionTerapia sesionTerapia) { this.sesionTerapia = sesionTerapia; }
}