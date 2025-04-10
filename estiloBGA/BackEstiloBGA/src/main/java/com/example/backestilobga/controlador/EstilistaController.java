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
public class EstilistaController {

    private final EstilistaServicio estilistaServicio;

    @Autowired
    public EstilistaController(EstilistaServicio estilistaServicio) {
        this.estilistaServicio = estilistaServicio;
    }

    @GetMapping("/list")
    public List<Estilista> consultarEstilistas() {
       return estilistaServicio.getAllEstilistas();
    }

    @GetMapping("/list/{id}")
    public Estilista getEstilistaById(@PathVariable Long id){
        return estilistaServicio.getEstilistaById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Estilista> addEstilista(@RequestBody Estilista estilista){
        Estilista newEstilista = estilistaServicio.saveEstilista(estilista);
        return new ResponseEntity <>(newEstilista, HttpStatus.OK);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Estilista> updateEstilista(@PathVariable Integer id, @RequestBody Estilista estilista){
        return estilistaServicio.getEstilistaById(id)
                .map(existingEstilista -> {
                    estilista.setId(id);
                    return new ResponseEntity<>(estilistaServicio.saveEstilista(estilista), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstilista(@PathVariable Integer id) {
        return estilistaServicio.getEstilistaById(id)
                .map(estilista -> {
                    estilistaServicio.deleteEstilista(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Estilista>> searchEstilistasByNombre(@RequestParam String nombre) {
        List<Estilista> estilistas = estilistaServicio.findByNombre(nombre);
        return new ResponseEntity<>(estilistas, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Estilista>> getActiveEstilistas() {
        List<Estilista> activeEstilistas = estilistaServicio.findActiveEstilistas();
        return new ResponseEntity<>(activeEstilistas, HttpStatus.OK);
    }

     */
}

