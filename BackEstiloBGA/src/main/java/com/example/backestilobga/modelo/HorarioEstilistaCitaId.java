package com.example.backestilobga.modelo;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HorarioEstilistaCitaId implements Serializable {

    private Long horarioId;
    private Long citaId;

    // Constructores
    public HorarioEstilistaCitaId() {
    }

    public HorarioEstilistaCitaId(Long horarioId, Long citaId) {
        this.horarioId = horarioId;
        this.citaId = citaId;
    }

    // Getters y setters
    public Long getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Long horarioId) {
        this.horarioId = horarioId;
    }

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    // Implementaciones de equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioEstilistaCitaId that = (HorarioEstilistaCitaId) o;
        return Objects.equals(horarioId, that.horarioId) &&
                Objects.equals(citaId, that.citaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horarioId, citaId);
    }
}

