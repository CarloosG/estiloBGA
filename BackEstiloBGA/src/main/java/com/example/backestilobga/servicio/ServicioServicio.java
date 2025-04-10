package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Servicio;
import com.example.backestilobga.repositorio.ServicioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServicioServicio implements IServicioServicio{

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Override
    public List<Servicio> getServicios() {
        return servicioRepositorio.findAll();
    }

    @Override
    public Servicio getServicioPorId(Long id) {
        return servicioRepositorio.findById(id).orElse(null);
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepositorio.save(servicio);
    }

    @Override
    public void eliminarServicio(Long id) {
        servicioRepositorio.deleteById(id);
    }
}
