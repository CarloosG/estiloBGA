package com.example.backestilobga.modelo;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = Cita.TABLE_NAME)
public class Cita {

    public static final String TABLE_NAME = "cita";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    private String nombre_cliente;
    private String nombre_estilista;
    private String servicio;
    private Date fecha;
    private int precio_servicio;
    private boolean promocion;

    public Cita() {
    }

    public Cita(int idCita, String nombre_cliente, String nombre_estilista, String servicio, Date fecha, int precio_servicio, boolean promocion) {
        this.idCita = idCita;
        this.nombre_cliente = nombre_cliente;
        this.nombre_estilista = nombre_estilista;
        this.servicio = servicio;
        this.fecha = fecha;
        this.precio_servicio = precio_servicio;
        this.promocion = promocion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_estilista() {
        return nombre_estilista;
    }

    public void setNombre_estilista(String nombre_estilista) {
        this.nombre_estilista = nombre_estilista;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(int precio_servicio) {
        this.precio_servicio = precio_servicio;
    }

    public boolean isPromocion() {
        return promocion;
    }

    public void setPromocion(boolean promocion) {
        this.promocion = promocion;
    }
}
