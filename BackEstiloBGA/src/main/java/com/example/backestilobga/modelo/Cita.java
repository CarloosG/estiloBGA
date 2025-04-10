package com.example.backestilobga.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Cita.TABLE_NAME)
public class Cita {

    public static final String TABLE_NAME = "Cita";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id")
    private Long id;

    //Relacion Muchos a Uno con Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //Relacion Muchos a Uno con Estilista
    @ManyToOne
    @JoinColumn(name = "estilista_id")
    private Estilista estilista;

    @Column(name = "fecha_cita")
    private LocalDateTime fechaCita;

    //Relacion Uno a Muchos con Servicio a través de Cita_Servicio
    @OneToMany(mappedBy = "cita", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<CitaServicioTable> citaServicios = new HashSet<>();

    //Relación Muchos a Muchos con HorarioEstilista
    // Reemplaza la relación ManyToMany con OneToMany hacia la tabla de unión
    @JsonIgnore
    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorarioEstilistaCita> horarioEstilistaCitas = new HashSet<>();

    // Relación para Cita_reporte
    @OneToMany(mappedBy = "cita", cascade = CascadeType.REMOVE)
    private Set<Cita_reporte> citaReportes = new HashSet<>();

    @OneToOne(mappedBy = "cita", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Pago pago;

    // Constuctor vacio
    public Cita() {}

    // Constructor con todos los atributos (excepto colecciones y el ID generado)
    public Cita(Cliente cliente, Estilista estilista, LocalDateTime fechaCita, Pago pago) {
        this.cliente = cliente;
        this.estilista = estilista;
        this.fechaCita = fechaCita;
        this.pago = pago;
    }


    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Set<CitaServicioTable> getCitaServicios() {
        return citaServicios;
    }

    public void setCitaServicios(Set<CitaServicioTable> citaServicios) {
        this.citaServicios = citaServicios;
    }

    public Set<HorarioEstilistaCita> getHorarioEstilistaCitas() {
        return horarioEstilistaCitas;
    }

    public void setHorarioEstilistaCitas(Set<HorarioEstilistaCita> horarioEstilistaCitas) {
        this.horarioEstilistaCitas = horarioEstilistaCitas;
    }

    public Set<Cita_reporte> getCitaReportes() {
        return citaReportes;
    }

    public void setCitaReportes(Set<Cita_reporte> citaReportes) {
        this.citaReportes = citaReportes;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}