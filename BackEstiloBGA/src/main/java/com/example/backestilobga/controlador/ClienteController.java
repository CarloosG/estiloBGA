package com.example.backestilobga.controlador;

import com.example.backestilobga.modelo.Cliente;
import com.example.backestilobga.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    // Buscar todos los clientes
    @GetMapping("/list")
    public List<Cliente> consultarClientes() {
        return clienteServicio.buscarTodosClientes();
    }

    // Buscar cliente por id
    @GetMapping("/list/{id}")
    public Cliente buscarClientePorId(@PathVariable Long id) {
        return clienteServicio.buscarClientePorId(id);
    }

    // Agregar una cita
    @PostMapping("/")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteServicio.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // Editar una cita
    @PutMapping("/")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente) {
        Long id = cliente.getId();
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Cliente clienteExistente = clienteServicio.buscarClientePorId(id);
        if(clienteExistente != null) {
            Cliente clienteActualizado = clienteServicio.guardarCliente(cliente);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una cita por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable Long id) {
        Cliente clienteExistente = clienteServicio.buscarClientePorId(id);
        if(clienteExistente != null) {
            clienteServicio.eliminarCliente(id);
            return new ResponseEntity<>(clienteExistente, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
