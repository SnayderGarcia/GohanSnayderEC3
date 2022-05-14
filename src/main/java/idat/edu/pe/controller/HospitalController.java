package idat.edu.pe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import idat.edu.pe.dto.HospitalDTOResponse;
import idat.edu.pe.dto.HospitalRequestDTO;
import idat.edu.pe.service.HospitalService;

@RestController
@RequestMapping("/Hospital")
public class HospitalController {
	
	@Autowired
	private HospitalService service;
	
	
	@RequestMapping(method = RequestMethod.GET, path =  "/listar")
	public ResponseEntity<List<HospitalDTOResponse>>listar(){
		
		return new ResponseEntity<List<HospitalDTOResponse>>(service.listar(), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/listar/{id}")
	public @ResponseBody ResponseEntity<HospitalDTOResponse> productById(@PathVariable Integer id){
		HospitalDTOResponse hospital = service.hospitalById(id);
		if(hospital != null) {
			return new ResponseEntity<HospitalDTOResponse>(hospital, HttpStatus.OK);

		}
		return new ResponseEntity<HospitalDTOResponse>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(method = RequestMethod.POST, path = "/guardar")
	public ResponseEntity<Void> guardar(@RequestBody HospitalRequestDTO hospital){
		service.guardar(hospital);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, path = "/editar")
	public ResponseEntity<Void> editar(@RequestBody HospitalRequestDTO hospital){
		HospitalDTOResponse Hospital = service.hospitalById(hospital.getIdRequest());
		if(Hospital != null) {
			service.editar(hospital);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, path ="/Eliminar/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		HospitalDTOResponse hospitals = service.hospitalById(id);
		
		if(hospitals != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	
	}

}
