package idat.edu.pe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.dto.HospitalDTOResponse;
import idat.edu.pe.dto.HospitalRequestDTO;
import idat.edu.pe.model.Hospital;
import idat.edu.pe.repository.HospitalRepository;

@Service
public class HospitalService implements HospitalServiceImplement {

	
	@Autowired
	private HospitalRepository repository;
	
	@Override
	public void guardar(HospitalRequestDTO h) {
		Hospital hospital = new Hospital();
		hospital.setIdHospital(h.getIdRequest());
		hospital.setNombre(h.getNombreHospital());
		hospital.setDescripcion(h.getDescripcionHospital());
		hospital.setDistrito(h.getDistritoHospital());


		repository.save(hospital);

	}

	@Override
	public void eliminar(Integer id) {
		repository.deleteById(id);

	}

	@Override
	public void editar(HospitalRequestDTO h) {
		Hospital hospital = new Hospital();
		hospital.setIdHospital(h.getIdRequest());
		hospital.setIdHospital(h.getIdRequest());
		hospital.setNombre(h.getNombreHospital());
		hospital.setDescripcion(h.getDescripcionHospital());
		hospital.setDistrito(h.getDistritoHospital());
		repository.saveAndFlush(hospital);

	}

	@Override
	public List<HospitalDTOResponse> listar() {
		List<HospitalDTOResponse> dto = new ArrayList<HospitalDTOResponse>();
		HospitalDTOResponse DTOHospital = null;
		
		List<Hospital> hospital = repository.findAll();
		
		for (Hospital hospita: hospital) {
			
			DTOHospital = new HospitalDTOResponse();
			DTOHospital.setIdResponse(hospita.getIdHospital());
			DTOHospital.setNombreHospital(hospita.getNombre());
			DTOHospital.setDescripcionHospital(hospita.getDescripcion());
			DTOHospital.setDistritoHospital(hospita.getDistrito());

			
			dto.add(DTOHospital);
			
			
		}
		
		return dto;
		
	}

	@Override
	public HospitalDTOResponse hospitalById(Integer id) {
		Hospital hospital = repository.findById(id).orElse(null);
		HospitalDTOResponse DTOHospital = new HospitalDTOResponse();
		

		
		DTOHospital = new HospitalDTOResponse();
		DTOHospital.setIdResponse(hospital.getIdHospital());
		DTOHospital.setNombreHospital(hospital.getNombre());
		DTOHospital.setDescripcionHospital(hospital.getDescripcion());
		DTOHospital.setDistritoHospital(hospital.getDistrito());

		
		return DTOHospital;
	}

}
