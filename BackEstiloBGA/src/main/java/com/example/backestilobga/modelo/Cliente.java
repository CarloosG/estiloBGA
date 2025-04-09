package com.example.backestilobga.modelo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente {

    public static final String TABLE_NAME = "Cliente";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    //Relacion uno a uno con usuario
    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    // Relacion uno a muchos con Cita
    @OneToMany(mappedBy = "cliente")
    private Set<Cita> citas = new HashSet<>(); //aca hay un problema, revisar que es

    // Constructores
    public Cliente() {}

    //Constructor con Usuario
    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    // Método helper para agregar una cita
    public void addCita(Cita cita) {
        citas.add(cita);
        cita.setCliente(this);
    }

    // Método helper para remover una cita
    public void removeCita(Cita cita) {
        citas.remove(cita);
        cita.setCliente(null);
    }
}
