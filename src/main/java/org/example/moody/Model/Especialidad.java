package org.example.moody.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEspecialidad")
    private Integer idEspecialidad;  // Cambiado de 'id' a 'idEspecialidad'

    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Column(name = "Nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    // Relación OneToMany con Profesional (opcional, agrega si necesitas)
    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.List<Profesional> profesionales;

    public Especialidad() {}

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters actualizados
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public java.util.List<Profesional> getProfesionales() {
        return profesionales;
    }

    public void setProfesionales(java.util.List<Profesional> profesionales) {
        this.profesionales = profesionales;
    }

    @Override
    public String toString() {
        return "Especialidad{" + "idEspecialidad=" + idEspecialidad + ", nombre='" + nombre + '\'' + '}';
    }
}