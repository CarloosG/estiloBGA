package com.example.backestilobga.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {

    public static final String TABLE_NAME = "Usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private long id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "password")
    private String password;

    @Column(name = "celular")
    private String celular;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private RolUsuario rol;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    // Constructor vacio
    public Usuario() {
    }

    //Constructor completo

    public Usuario(String nombres, String apellidos, String numeroDocumento, String correoElectronico, String password, String celular, RolUsuario rol, LocalDate fechaRegistro) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.celular = celular;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Enumeración para el rol del usuario
    public enum RolUsuario {
        admin, cliente, estilista
    }
}
