package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Estilista;
import com.example.backestilobga.repositorio.EstilistaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstilistaServicio implements IEstilistaServicio {

    @Autowired
    private EstilistaRepositorio estilistaRepositorio;

    @Override
    public List<Estilista> getAllEstilistas() {
        return estilistaRepositorio.findAll();
    }

    @Override
    public Estilista getEstilistaById(Long id) {
        return estilistaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Estilista saveEstilista(Estilista estilista) {
        return estilistaRepositorio.save(estilista);
    }

    @Override
    public void deleteEstilista(Long id) {
        estilistaRepositorio.deleteById(id);
    }
}
