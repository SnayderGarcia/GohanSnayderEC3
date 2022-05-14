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

import idat.edu.pe.dto.ClienteDTOResponse;
import idat.edu.pe.dto.ClienteRequestDTO;
import idat.edu.pe.service.ClienteService;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	
	@RequestMapping(method = RequestMethod.GET, path =  "/listar")
	public ResponseEntity<List<ClienteDTOResponse>>listar(){
		
		return new ResponseEntity<List<ClienteDTOResponse>>(service.listar(), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/listar/{id}")
	public @ResponseBody ResponseEntity<ClienteDTOResponse> productById(@PathVariable Integer id){
		ClienteDTOResponse cliente = service.clienteById(id);
		if(cliente != null) {
			return new ResponseEntity<ClienteDTOResponse>(cliente, HttpStatus.OK);

		}
		return new ResponseEntity<ClienteDTOResponse>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(method = RequestMethod.POST, path = "/guardar")
	public ResponseEntity<Void> guardar(@RequestBody ClienteRequestDTO cliente){
		service.guardarCliente(cliente);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, path = "/editar")
	public ResponseEntity<Void> editar(@RequestBody ClienteRequestDTO cliente){
		ClienteDTOResponse client = service.clienteById(cliente.getIdRequest());
		if(client != null) {
			service.editarProducto(cliente);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, path ="/Eliminar/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		ClienteDTOResponse cliente= service.clienteById(id);
		
		if(cliente != null) {
			service.eliminarCliente(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	
	}
}
