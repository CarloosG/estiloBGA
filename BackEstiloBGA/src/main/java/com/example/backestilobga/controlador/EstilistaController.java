package com.example.backestilobga.controlador;

import com.example.backestilobga.modelo.Estilista;
import com.example.backestilobga.servicio.EstilistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estilistas")
@CrossOrigin("*")
public class EstilistaController {

    @Autowired
    private EstilistaServicio estilistaServicio;

    //Buscar todos los estilistas
    @GetMapping("/list")
    public List<Estilista> consultarEstilistas(){
        return estilistaServicio.getAllEstilistas();
    }

    // Buscar estilista por id
    @GetMapping("/list/{id}")
    public Estilista getEstilistaById(@PathVariable Long id){
        return estilistaServicio.getEstilistaById(id);
    }

    // Agregar un estilista
    @PostMapping("/")
    public ResponseEntity<Estilista> addEstilista(@RequestBody Estilista estilista){
        Estilista nuevoEstilista = estilistaServicio.saveEstilista(estilista);
        return new ResponseEntity <>(nuevoEstilista, HttpStatus.CREATED);
    }

    //Editar un estilista
    @PutMapping("/")
    public ResponseEntity<Estilista> editarEstilista(@RequestBody Estilista estilista){
        Long id = estilista.getId();
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Estilista estilistaExistente = estilistaServicio.getEstilistaById(id);
        if (estilistaExistente != null) {
            Estilista estilistaActualizado = estilistaServicio.saveEstilista(estilista);
            return new ResponseEntity<>(estilistaActualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Eliminar un estilista por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Estilista> eliminarEstilista(@PathVariable Long id){
        Estilista estilistaExistente = estilistaServicio.getEstilistaById(id);
        if (estilistaExistente != null) {
            estilistaServicio.deleteEstilista(id);
            return new ResponseEntity<>(estilistaExistente, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}