package idat.edu.pe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.dto.ClienteDTOResponse;
import idat.edu.pe.dto.ClienteRequestDTO;
import idat.edu.pe.model.Cliente;
import idat.edu.pe.repository.ClienteReposirtory;

@Service
public class ClienteService implements ClienteServiceImplement{

	@Autowired
	private ClienteReposirtory repository;

	
	
	@Override
	public void guardarCliente(ClienteRequestDTO c) {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(c.getIdRequest());
		cliente.setNombre(c.getNombreCliente());
		cliente.setCelular(c.getCelularCliente());

		repository.save(cliente);
		
	}

	@Override
	public void eliminarCliente(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public void editarProducto(ClienteRequestDTO c) {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(c.getIdRequest());
		cliente.setNombre(c.getNombreCliente());
		cliente.setCelular(c.getCelularCliente());
		repository.saveAndFlush(cliente);
		
	}

	@Override
	public List<ClienteDTOResponse> listar() {
		List<ClienteDTOResponse> dto = new ArrayList<ClienteDTOResponse>();
		ClienteDTOResponse DTOCliente = null;
		
		List<Cliente> cliente = repository.findAll();
		
		for (Cliente client: cliente) {
			
			DTOCliente = new ClienteDTOResponse();
			DTOCliente.setIdResponse(client.getIdCliente());
			DTOCliente.setNombreCliente(client.getNombre());
			DTOCliente.setCelularCliente(client.getCelular());

			
			dto.add(DTOCliente);
			
			
		}
		
		return dto;
	}

	@Override
	public ClienteDTOResponse clienteById(Integer id) {
		Cliente cliente = repository.findById(id).orElse(null);
		ClienteDTOResponse DTOCliente = new ClienteDTOResponse();
		

		
		DTOCliente = new ClienteDTOResponse();
		DTOCliente.setIdResponse(cliente.getIdCliente());
		DTOCliente.setNombreCliente(cliente.getNombre());
		DTOCliente.setCelularCliente(cliente.getCelular());

		
		return DTOCliente;
	}
	

}
