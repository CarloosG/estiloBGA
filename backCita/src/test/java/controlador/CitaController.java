package controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import modelo.cita;

import servicio.CitaServicio;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/citas")
public class CitaController {
	@Autowired
	private CitaServicio citaService;
	@GetMapping("/list")
	public List <cita> consultarTodo(){
		return (citaService.getCitas());
	}
	@GetMapping("/list/{id}")
	public cita buscarPorId(@PathVariable Integer id) {
		return citaService.getCita(id);
	}
	@PostMapping("/")
	public ResponseEntity<cita> agregar(@RequestBody cita cita){
		cita obj = citaService.grabarCita(cita);
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<cita> editar(@RequestBody cita cita){
		cita obj = citaService.getCita(cita.getIdcita());
		if(obj != null) {
			obj.setNombre_cliente(cita.getNombre_cliente());
			obj.setNombre_estilista(cita.getNombre_estilista());
			obj.setServicio(cita.getServicio());
			obj.setPrecio_servicio(cita.getPrecio_servicio());
			obj.setPromocion(cita.isPromocion());
			citaService.grabarCita(obj);
		} else {
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<cita> eliminar(@PathVariable Integer id){
		cita obj = citaService.getCita(id);
		if(obj != null) {
			citaService.delete(id);		
		}else {
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<>(obj, HttpStatus.OK);			

	}
	
}
