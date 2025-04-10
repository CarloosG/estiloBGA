package com.example.backestilobga.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Estilista.TABLE_NAME)
public class Estilista {

    public static final String TABLE_NAME = "Estilista";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estilista_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL) // O CascadeType.PERSIST
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @Column(name = "anios_experiencia")
    private Integer aniosExperiencia;

    @Column(name = "especializacion")
    private String especializacion;

    @OneToMany(mappedBy = "estilista")
    @JsonIgnore
    private Set<Cita> citas = new HashSet<>();

    @OneToMany(mappedBy = "estilista")
    @JsonIgnore
    private Set<HorarioEstilista> horarios = new HashSet<>();

    // Constructores
    public Estilista() {
    }

    public Estilista(Usuario usuario, Integer aniosExperiencia, String especializacion, Set<Cita> citas, Set<HorarioEstilista> horarios) {
        this.usuario = usuario;
        this.aniosExperiencia = aniosExperiencia;
        this.especializacion = especializacion;
        this.citas = citas;
        this.horarios = horarios;
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

    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public Set<HorarioEstilista> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<HorarioEstilista> horarios) {
        this.horarios = horarios;
    }

    // Métodos helper para la relación con HorarioEstilista (opcional)

    public void addHorario(HorarioEstilista horario) {
        this.horarios.add(horario);
        horario.setEstilista(this);
    }

    public void removeHorario(HorarioEstilista horario) {
        this.horarios.remove(horario);
        horario.setEstilista(null);
    }

    // Métodos helper para la relación con Reporte (opcional)

    /*public void addReporte(Reporte reporte) {
        this.reportes.add(reporte);
        reporte.setEstilista(this);
    }

    public void removeReporte(Reporte reporte) {
        this.reportes.remove(reporte);
        reporte.setEstilista(null);
    }*/
}
