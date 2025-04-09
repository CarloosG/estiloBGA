package com.example.backestilobga.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

// Clase para la clave primaria compuesta de CitaServicio
@Embeddable
public class CitaServicioId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cita_id")
    private Long citaId;

    @Column(name = "servicio_id")
    private Long servicioId;

    // Constructores
    public CitaServicioId() {}

    public CitaServicioId(Long citaId, Long servicioId) {
        this.citaId = citaId;
        this.servicioId = servicioId;
    }

    // equals, hashCode y getters/setters
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaServicioId that = (CitaServicioId) o;
        return Objects.equals(citaId, that.citaId) &&
                Objects.equals(servicioId, that.servicioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citaId, servicioId);
    }

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }
}
