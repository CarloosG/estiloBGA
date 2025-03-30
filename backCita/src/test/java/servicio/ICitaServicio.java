package servicio;

import java.util.List;

import modelo.cita;

public interface ICitaServicio {
	public List<cita> getCitas();
	public cita getCita(Integer id);
	public cita grabarCita(cita cita);
	public void delete(Integer id);
	
}
