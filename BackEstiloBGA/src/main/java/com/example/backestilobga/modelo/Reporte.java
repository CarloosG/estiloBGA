package com.example.backestilobga.modelo;
import com.example.backestilobga.servicio.CitaServicio;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = Reporte.TABLE_NAME)
public class Reporte {
    public static final String TABLE_NAME = "Reporte";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reporte_id")
    private Long id;

    //Relacion Muchos a Uno con Estilista
    @ManyToOne
    @JoinColumn(name = "estilista_id")
    private Estilista estilista;

    @Column(name = "fecha_inicio")
    private Date fecha_inicio;

    @Column(name = "fecha_fin")
    private Date fecha_fin;

    @Column(name = "cantidad_citas")
    private int cantidad_citas;

    @Column(name = "total_ingresos", precision = 10, scale = 2)
    private BigDecimal total_ingresos;

    @Column(name = "servicio_top1")
    private String correoElectronico;

    @Column(name = "fecha_generado")
    private LocalDateTime fecha_generado;

    @OneToMany(mappedBy = "reporte", cascade = CascadeType.ALL)
    private Set<Cita_reporte> citaReportes = new HashSet<>();

    public Reporte() {}

    public Reporte(Long id, Estilista estilista, Date fecha_inicio, Date fecha_fin, int cantidad_citas, BigDecimal total_ingresos, String correoElectronico, LocalDateTime fecha_generado, Set<Cita_reporte> citaReportes) {
        this.id = id;
        this.estilista = estilista;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.cantidad_citas = cantidad_citas;
        this.total_ingresos = total_ingresos;
        this.correoElectronico = correoElectronico;
        this.fecha_generado = fecha_generado;
        this.citaReportes = citaReportes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getCantidad_citas() {
        return cantidad_citas;
    }

    public void setCantidad_citas(int cantidad_citas) {
        this.cantidad_citas = cantidad_citas;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public BigDecimal getTotal_ingresos() {
        return total_ingresos;
    }

    public void setTotal_ingresos(BigDecimal total_ingresos) {
        this.total_ingresos = total_ingresos;
    }

    public LocalDateTime getFecha_generado() {
        return fecha_generado;
    }

    public void setFecha_generado(LocalDateTime fecha_generado) {
        this.fecha_generado = fecha_generado;
    }

    public Set<Cita_reporte> getCitaReportes() {
        return citaReportes;
    }

    public void setCitaReportes(Set<Cita_reporte> citaReportes) {
        this.citaReportes = citaReportes;
    }
}