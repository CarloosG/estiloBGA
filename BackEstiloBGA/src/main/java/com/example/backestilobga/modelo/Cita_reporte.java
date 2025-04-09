package com.example.backestilobga.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = Cita_reporte.TABLE_NAME)
public class Cita_reporte {
    public static final String TABLE_NAME = "Cita_reporte";

    @EmbeddedId
    private Cita_reporteId id;

    @ManyToOne
    @MapsId("citaId")
    @JoinColumn(name = "cita_id")
    private Cita cita;

    @ManyToOne
    @MapsId("reporteId")
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;


}