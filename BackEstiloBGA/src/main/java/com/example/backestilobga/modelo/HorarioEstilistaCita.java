package com.example.backestilobga.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = HorarioEstilistaCita.TABLE_NAME)
public class HorarioEstilistaCita {

    public static final String TABLE_NAME = "Horario_Estilista_Cita";

    @EmbeddedId
    private HorarioEstilistaCitaId id;

    @ManyToOne
    @MapsId("horarioId")
    @JoinColumn(name = "horario_id")
    private HorarioEstilista horarioEstilista;

    @ManyToOne
    @MapsId("citaId")
    @JoinColumn(name = "cita_id")
    private Cita cita;

    // Constructores
    public HorarioEstilistaCita() {
    }

    public HorarioEstilistaCita(HorarioEstilista horarioEstilista, Cita cita) {
        this.horarioEstilista = horarioEstilista;
        this.cita = cita;
        this.id = new HorarioEstilistaCitaId(horarioEstilista.getId(), cita.getId());
    }

    //Getters y setters
    public HorarioEstilistaCitaId getId() {
        return id;
    }

    public void setId(HorarioEstilistaCitaId id) {
        this.id = id;
    }

    public HorarioEstilista getHorarioEstilista() {
        return horarioEstilista;
    }

    public void setHorarioEstilista(HorarioEstilista horarioEstilista) {
        this.horarioEstilista = horarioEstilista;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
