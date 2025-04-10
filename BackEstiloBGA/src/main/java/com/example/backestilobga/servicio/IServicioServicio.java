package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Servicio;

import java.util.List;

public interface IServicioServicio {

    //Buscar todos los servicios
    public List<Servicio> getServicios();

    // Buscar servicio por id
    public Servicio getServicioPorId(Long id);

    // Guardar servicio
    public Servicio guardarServicio(Servicio servicio);

    // Eliminar servicio por id
    public void eliminarServicio(Long id);
}