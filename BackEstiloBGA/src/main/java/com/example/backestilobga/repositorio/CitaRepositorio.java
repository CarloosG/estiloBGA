package com.example.backestilobga.repositorio;

import com.example.backestilobga.modelo.Cita;
import com.example.backestilobga.modelo.Cliente;
import com.example.backestilobga.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    Optional<List<Cita>> findByCliente(Cliente cliente);
}
