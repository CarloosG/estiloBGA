package com.example.backestilobga.modelo;
import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = Pago.TABLE_NAME)
public class Pago {
    public static final String TABLE_NAME = "Pago";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_id", unique = true)
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "fecha_pago")
    private Date fecha_pago;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago")
    private MetodoPago metodoPago;

    public Pago(){}

    public Pago(Long id, Cita cita, Cliente cliente, Date fecha_pago, MetodoPago metodoPago) {
        this.id = id;
        this.cita = cita;
        this.cliente = cliente;
        this.fecha_pago = fecha_pago;
        this.metodoPago = metodoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    public enum MetodoPago {
        efectivo, tarjeta, transferencia
    }
}