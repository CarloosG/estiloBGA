package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Estilista;

import java.util.List;
import java.util.Optional;

public interface IEstilistaServicio {

    List<Estilista> getAllEstilistas();

    Estilista getEstilistaById(Long id);

    Estilista saveEstilista(Estilista estilista);

    void deleteEstilista(Long id);
}
