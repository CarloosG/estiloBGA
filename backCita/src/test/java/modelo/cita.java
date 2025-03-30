package modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name=cita.TABLE_NAME)
public class cita {
	public static final String TABLE_NAME = "cita";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcita;
	private String nombre_cliente;
	private String nombre_estilista;
	private String servicio;
	private Date fecha;
	public cita() {
		
	}
	
	public cita(int idcliente, String nombre_cliente, String nombre_estilista, String servicio, Date fecha,
			int precio_servicio, boolean promocion) {
		super();
		this.idcita = idcliente;
		this.nombre_cliente = nombre_cliente;
		this.nombre_estilista = nombre_estilista;
		this.servicio = servicio;
		this.fecha = fecha;
		this.precio_servicio = precio_servicio;
		this.promocion = promocion;
	}
	
	public int getIdcita() {
		return idcita;
	}
	public void setIdcita(int idcliente) {
		this.idcita = idcliente;
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
	private int precio_servicio;
	private boolean promocion;
	@Override
	public String toString() {
		return "cita [idcliente=" + idcita + ", nombre_cliente=" + nombre_cliente + ", nombre_estilista="
				+ nombre_estilista + ", servicio=" + servicio + ", fecha=" + fecha + ", precio_servicio="
				+ precio_servicio + ", promocion=" + promocion + "]";
	} 
	
	

}

