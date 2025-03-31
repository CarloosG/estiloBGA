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

    @GetMapping("/list")
    public List<Cita> consultarTodo() {
        return citaServicio.getCitas();
    }

    @GetMapping("/list/{id}")
    public Cita buscarCitaPorId(@PathVariable Integer id) {
        return citaServicio.getCita(id);
    }

    @PostMapping("/")
    public ResponseEntity<Cita> agregarCita(@RequestBody Cita cita) {
        Cita obj = citaServicio.guardarCita( cita );
        return new ResponseEntity<>( obj, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Cita> editarCita(@RequestBody Cita cita) {
        Cita obj = citaServicio.getCita(cita.getIdCita());
        if(obj != null) {
            obj.setNombre_cliente(cita.getNombre_cliente());
            obj.setNombre_estilista(cita.getNombre_estilista());
            obj.setServicio(cita.getServicio());
            obj.setPrecio_servicio(cita.getPrecio_servicio());
            obj.setPromocion(cita.isPromocion());
            citaServicio.guardarCita(obj);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cita> eliminarCita(@PathVariable Integer id) {
        Cita obj = citaServicio.getCita(id);
        if(obj != null) {
            citaServicio.eliminarCita(id);
        }else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
