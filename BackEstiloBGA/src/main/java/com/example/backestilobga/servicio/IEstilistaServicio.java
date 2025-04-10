package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Estilista;

import java.util.List;

public interface IEstilistaServicio {

    //Listar todos los estilistas
    List<Estilista> getAllEstilistas();

    //Listar estilista por id
    Estilista getEstilistaById(Long id);

    //Guardar estilista
    Estilista saveEstilista(Estilista estilista);

    //Borrar estilista
    void deleteEstilista(Long id);
}
