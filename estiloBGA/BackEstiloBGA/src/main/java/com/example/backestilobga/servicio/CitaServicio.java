package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Cita;
import com.example.backestilobga.repositorio.CitaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CitaServicio implements ICitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Override
    public List<Cita> getCitas() {
        return citaRepositorio.findAll();
    }

    @Override
    public Cita getCita(Integer id) {
        return citaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepositorio.save(cita);
    }

    @Override
    public void eliminarCita(Integer id) {
        citaRepositorio.deleteById(id);
    }
}
