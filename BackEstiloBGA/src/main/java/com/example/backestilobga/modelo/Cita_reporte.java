package BackEstiloBGA.src.main.java.com.example.backestilobga.modelo;

import com.example.backestilobga.servicio.CitaServicio;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Cita_reporte.TABLE_NAME)
public class Cita_reporte {
    public static final String TABLE_NAME = "Cita_reporte";

    @EmbeddedId
    private Cita_reporte id;

    @ManyToOne
    @MapsId("citaId")
    @JoinColumn(name = "cita_id")
    private Cita cita;

    @ManyToOne
    @MapsId("reporteId")
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;


}
