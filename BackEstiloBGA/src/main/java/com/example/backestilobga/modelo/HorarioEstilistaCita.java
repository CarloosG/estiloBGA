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
}
