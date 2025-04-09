package com.example.backestilobga.modelo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Servicio.TABLE_NAME)
public class Servicio {

    public static final String TABLE_NAME = "Servicio";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private Long id;

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    @Column(name = "condiciones_previas")
    private String condicionesPrevias;

    //Relacion Muchos a muchos con cita a traves de Cita_Servicio
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private Set<CitaServicioTable> citaServicioTables = new HashSet<>();

    // Constructores
    public Servicio() {}

    public Servicio(String nombreServicio, String condicionesPrevias) {
        this.nombreServicio = nombreServicio;
        this.condicionesPrevias = condicionesPrevias;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getCondicionesPrevias() {
        return condicionesPrevias;
    }

    public void setCondicionesPrevias(String condicionesPrevias) {
        this.condicionesPrevias = condicionesPrevias;
    }

    public Set<CitaServicioTable> getCitaServicioTables() {
        return citaServicioTables;
    }

    public void setCitaServicioTables(Set<CitaServicioTable> citaServicioTables) {
        this.citaServicioTables = citaServicioTables;
    }
}