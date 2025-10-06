package org.example.moody.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Profesional")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProfesional")
    private Integer idProfesional;  // Renombrado de 'id' para consistencia

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "La especialidad es obligatoria")
    @Column(name = "IDespecialidad", nullable = false)  // Campo directo para FK
    private Integer idEspecialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDespecialidad", insertable = false, updatable = false)  // Relación sin duplicar FK
    private Especialidad especialidad;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    @Column(name = "Telefono", length = 20)
    private String telefono;

    public Profesional() {
    }

    public Profesional(String nombre, Integer idEspecialidad, String email, String telefono) {
        this.nombre = nombre;
        this.idEspecialidad = idEspecialidad;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Integer getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(Integer idProfesional) {
        this.idProfesional = idProfesional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdEspecialidad() {  // Nuevo: Getter para FK directa
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        if (especialidad != null) {
            this.idEspecialidad = especialidad.getIdEspecialidad();  // Sincroniza FK
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Profesional{" +
                "idProfesional=" + idProfesional +
                ", nombre='" + nombre + '\'' +
                ", idEspecialidad=" + idEspecialidad +
                ", especialidad=" + (especialidad != null ? especialidad.getNombre() : null) +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}