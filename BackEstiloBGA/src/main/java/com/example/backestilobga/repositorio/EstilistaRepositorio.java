package com.example.backestilobga.repositorio;

import com.example.backestilobga.modelo.Estilista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstilistaRepositorio extends JpaRepository<Estilista, Long> {
}
