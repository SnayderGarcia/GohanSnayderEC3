package idat.edu.pe.service;

import java.util.List;

import idat.edu.pe.dto.ClienteDTOResponse;
import idat.edu.pe.dto.ClienteRequestDTO;

public interface ClienteServiceImplement {
	
	public void guardarCliente(ClienteRequestDTO c);
	public void eliminarCliente(Integer id);
	public void editarProducto(ClienteRequestDTO c);
	public List<ClienteDTOResponse> listar();
	public ClienteDTOResponse clienteById(Integer id);

}
