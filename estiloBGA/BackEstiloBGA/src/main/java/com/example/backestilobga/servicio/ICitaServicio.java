package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Cita;

import java.util.List;

public interface ICitaServicio {

    public List<Cita> getCitas();

    public Cita getCita(Integer id);

    public Cita guardarCita(Cita cita);

    public void eliminarCita(Integer id);
}
