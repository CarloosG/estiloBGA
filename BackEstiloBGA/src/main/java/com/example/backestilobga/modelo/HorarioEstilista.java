package com.example.backestilobga.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = HorarioEstilista.TABLE_NAME)
public class HorarioEstilista {

    public static final String TABLE_NAME = "Horario_Estilista";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estilista_id")
    private Estilista estilista;

    @Column(name = "dia_semana")
    private String diaSemana;

    @Column(name = "intervalo_disponible")
    private String intervaloDisponible;

    // Reemplaza la relación ManyToMany con OneToMany hacia la tabla de unión
    @OneToMany(mappedBy = "horarioEstilista", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<HorarioEstilistaCita> horarioEstilistaCitas = new HashSet<>();

    // Constructores
    public HorarioEstilista() {
    }

    public HorarioEstilista(Estilista estilista, String diaSemana, String intervaloDisponible, Set<Cita> citas) {
        this.estilista = estilista;
        this.diaSemana = diaSemana;
        this.intervaloDisponible = intervaloDisponible;
    }

    // Getters y setters

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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getIntervaloDisponible() {
        return intervaloDisponible;
    }

    public void setIntervaloDisponible(String intervaloDisponible) {
        this.intervaloDisponible = intervaloDisponible;
    }

    public Set<HorarioEstilistaCita> getHorarioEstilistaCitas() {
        return horarioEstilistaCitas;
    }

    public void setHorarioEstilistaCitas(Set<HorarioEstilistaCita> horarioEstilistaCitas) {
        this.horarioEstilistaCitas = horarioEstilistaCitas;
    }


    // Métodos helper para la relación con Cita (opcional, para facilitar la manipulación)

    /*public void addCita(Cita cita) {
        this.citas.add(cita);
        cita.getHorariosEstilista().add(this); // Mantiene la bidireccionalidad
    }

    public void removeCita(Cita cita) {
        this.citas.remove(cita);
        cita.getHorariosEstilista().remove(this); // Mantiene la bidireccionalidad
    }*/
}
