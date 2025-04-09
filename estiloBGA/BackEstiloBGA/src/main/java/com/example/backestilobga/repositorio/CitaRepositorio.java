package com.example.backestilobga.repositorio;

import com.example.backestilobga.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Integer> {
}
