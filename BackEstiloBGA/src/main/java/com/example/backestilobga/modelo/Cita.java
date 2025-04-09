package com.example.backestilobga.modelo;

import com.example.backestilobga.servicio.CitaServicio;
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

    //Relacion Muchos a Muchos con Servicio a través de Cita_Servicio
    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL)
    private Set<CitaServicioTable> citaServicios = new HashSet<>();

    //Relación Muchos a Muchos con Horario_Estilista a través de Horario_Estilista_Cita
    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL)
    private Set<HorarioEstilistaCita> horarioEstilistaCitas = new HashSet<>();

    // Constuctor vacio
    public Cita() {}

    // Constructor con todos los atributos (excepto colecciones y el ID generado)
    public Cita(Cliente cliente, Estilista estilista, LocalDateTime fechaCita) {
        this.cliente = cliente;
        this.estilista = estilista;
        this.fechaCita = fechaCita;
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

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Set<CitaServicio> getCitaServicios() {
        return citaServicios;
    }

    public void setCitaServicios(Set<CitaServicio> citaServicios) {
        this.citaServicios = citaServicios;
    }

    public Set<HorarioEstilistaCita> getHorarioEstilistaCitas() {
        return horarioEstilistaCitas;
    }

    public void setHorarioEstilistaCitas(Set<HorarioEstilistaCita> horarioEstilistaCitas) {
        this.horarioEstilistaCitas = horarioEstilistaCitas;
    }
}