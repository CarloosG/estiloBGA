package com.example.backestilobga.servicio;

import com.example.backestilobga.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {

    // Buscar todos los clientes
    public List<Cliente> buscarTodosClientes();

    // Buscar cliente por id
    public Cliente buscarClientePorId(Long id);

    // Guardar cliente
    public Cliente guardarCliente(Cliente cliente);

    // Eliminar cliente por id
    public void eliminarCliente(Long id);
}
