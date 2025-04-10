package com.example.backestilobga.controlador;

import com.example.backestilobga.modelo.Servicio;
import com.example.backestilobga.servicio.ServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin("*")
public class ServicioController {

    @Autowired
    private ServicioServicio servicioServicio;

    // Buscar todos los servicios
    @GetMapping("/list")
    public List<Servicio> consultarServicios() {
        return servicioServicio.getServicios();
    }

    // Buscar servicio por id
    @GetMapping("/list/{id}")
    public Servicio buscarServicioPorId(@PathVariable Long id) {
        return servicioServicio.getServicioPorId(id);
    }

    // Agregar un servicio
    @PostMapping("/")
    public ResponseEntity<Servicio> agregarServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioServicio.guardarServicio(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    // Editar un servicio
    @PutMapping("/")
    public ResponseEntity<Servicio> editarServicio(@RequestBody Servicio servicio) {
        Long id = servicio.getId();
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Servicio servicioExistente = servicioServicio.getServicioPorId(id);
        if(servicioExistente != null) {
            Servicio servicioActualizado = servicioServicio.guardarServicio(servicio);
            return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un servicio por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Servicio> eliminarServicio(@PathVariable Long id) {
        Servicio servicioExistente = servicioServicio.getServicioPorId(id);
        if(servicioExistente != null) {
            servicioServicio.eliminarServicio(id);
            return new ResponseEntity<>(servicioExistente, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}