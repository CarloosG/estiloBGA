package com.example.backestilobga.controlador;

import com.example.backestilobga.modelo.Cita;
import com.example.backestilobga.servicio.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin("*")
public class CitaController {

    @Autowired
    private CitaServicio citaServicio;

    //Buscar todas las citas
    @GetMapping("/list")
    public List<Cita> consultarTodo() {
        return citaServicio.listarTodasCitas();
    }

    // Buscar cita por id
    @GetMapping("/list/{id}")
    public Cita buscarCitaPorId(@PathVariable Long id) {
        return citaServicio.getCitaById(id);
    }

    // Agregar una cita
    @PostMapping("/")
    public ResponseEntity<Cita> agregarCita(@RequestBody Cita cita) {
        Cita nuevaCita = citaServicio.guardarCita( cita );
        return new ResponseEntity<>( nuevaCita, HttpStatus.OK);
    }

    // Editar una cita
    @PutMapping("/")
    public ResponseEntity<Cita> editarCita(@RequestBody Cita cita) {
        Long id = cita.getId();
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Cita citaExistente = citaServicio.getCitaById(id);
        if (citaExistente != null) {
            Cita citaActualizada = citaServicio.guardarCita(cita);
            return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Cita> eliminarCita(@PathVariable Long id) {
        Cita citaExistente = citaServicio.getCitaById(id);
        if (citaExistente != null) {
            citaServicio.eliminarCita(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
