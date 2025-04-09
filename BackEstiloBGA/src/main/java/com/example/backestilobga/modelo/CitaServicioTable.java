package com.example.backestilobga.modelo;

import jakarta.persistence.*;

//Modelo para la tabla intermedia Cita_Servicio (relacion muchos a muchos con atributos adicionales
@Entity
@Table(name = CitaServicioTable.TABLE_NAME)
public class CitaServicioTable {

    public static final String TABLE_NAME = "CitaServicio";

    //Clave primaria compuesta
    @EmbeddedId
    private CitaServicioId id;

    // Relacion muchos a uno con cita
    @ManyToOne
    @MapsId("citaId") // Indica que este campo forma parte de la clave primaria
    @JoinColumn(name = "cita_id")
    private Cita cita;

    // Relacion muchos a uno con servicio
    @ManyToOne
    @MapsId("servicioId")
    @JoinColumn(name = "servicio_id") // Indica que este campo forma parte de la clave primaria
    private Servicio servicio;

    @Column(name = "precio_servicio")
    private Integer precioServicio;

    @Column(name = "tiempo_estimado_servicio")
    private Integer tiempoEstimadoServicio;

    @Column(name = "promocion")
    private Boolean promocion;

    // Constructores
    public CitaServicioTable() {
    }

    public CitaServicioTable(CitaServicioId id, Cita cita, Servicio servicio, Integer precioServicio, Integer tiempoEstimadoServicio, Boolean promocion) {
        this.id = id;
        this.cita = cita;
        this.servicio = servicio;
        this.precioServicio = precioServicio;
        this.tiempoEstimadoServicio = tiempoEstimadoServicio;
        this.promocion = promocion;
    }

    //Getters y setters

    public CitaServicioId getId() {
        return id;
    }

    public void setId(CitaServicioId id) {
        this.id = id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Integer getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(Integer precioServicio) {
        this.precioServicio = precioServicio;
    }

    public Integer getTiempoEstimadoServicio() {
        return tiempoEstimadoServicio;
    }

    public void setTiempoEstimadoServicio(Integer tiempoEstimadoServicio) {
        this.tiempoEstimadoServicio = tiempoEstimadoServicio;
    }

    public Boolean getPromocion() {
        return promocion;
    }

    public void setPromocion(Boolean promocion) {
        this.promocion = promocion;
    }
}
