package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Cita;

import java.util.List;

public interface ICitaServicio {

    // Buscar todas las citas
    public List<Cita> listarTodasCitas();

    // Buscar cita por id
    public Cita getCitaById(Long id);

    // Guardar cita
    public Cita guardarCita(Cita cita);

    // Eliminar cita por id
    public void eliminarCita(Long id);

    /*// Métodos específicos de negocio
    List<Cita> buscarCitasPorCliente(Long clienteId);
    List<Cita> buscarCitasPorEstilista(Long estilistaId);
    List<Cita> buscarCitasPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    List<Cita> buscarCitasClientePorFecha(Long clienteId, LocalDateTime inicio, LocalDateTime fin);
    List<Cita> buscarCitasEstilistaPorFecha(Long estilistaId, LocalDateTime inicio, LocalDateTime fin);

    // Lógica de negocio
    boolean verificarDisponibilidad(Long estilistaId, LocalDateTime inicio, LocalDateTime fin);
    Cita programarCita(Long clienteId, Long estilistaId, LocalDateTime fechaCita, List<Long> serviciosIds);
    boolean cancelarCita(Long citaId);
    */

}
