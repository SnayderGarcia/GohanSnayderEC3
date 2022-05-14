package idat.edu.pe.service;

import java.util.List;

import idat.edu.pe.dto.HospitalDTOResponse;
import idat.edu.pe.dto.HospitalRequestDTO;

public interface HospitalServiceImplement {
	
	public void guardar(HospitalRequestDTO h);
	public void eliminar (Integer id);
	public void editar(HospitalRequestDTO h);
	public List<HospitalDTOResponse> listar();
	public HospitalDTOResponse hospitalById(Integer id);

}
