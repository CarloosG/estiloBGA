package com.example.backestilobga.modelo;

import java.util.Objects;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

public class Cita_reporteId {

    private Long citaId;
    private Long reporteId;

    // Constructores
    public Cita_reporteId() {
    }

    public Cita_reporteId(Long citaId, Long reporteIdId) {

        this.citaId = citaId;
        this.reporteId = reporteId;
    }

    // Getters y setters

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Long getReporteId() {
        return reporteId;
    }

    public void setReporteId(Long reporteId) {
        this.reporteId = reporteId;
    }

    // Implementaciones de equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita_reporteId that = (Cita_reporteId) o;
        return Objects.equals(citaId, that.citaId) &&
                Objects.equals(reporteId, that.reporteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citaId,reporteId);
    }
}
