package servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import modelo.cita;
import repositorio.CitaRepositorio;

@Service
@Transactional
public class CitaServicio implements ICitaServicio {

	@Autowired
	private CitaRepositorio citaRepo;
	
	@Override
	public List<cita> getCitas() {
		return citaRepo.findAll();
	}

	@Override
	public cita getCita(Integer id) {
		return citaRepo.findById(id).orElse(null);
	}

	@Override
	public cita grabarCita(cita cita) {
		return citaRepo.save(cita);
	}

	@Override
	public void delete(Integer id) {
		citaRepo.deleteById(id);
	}

}
