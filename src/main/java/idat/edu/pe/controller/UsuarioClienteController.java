package idat.edu.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.edu.pe.dto.UsuarioClienteDTOResponse;
import idat.edu.pe.dto.UsuarioClienteRequestDTO;
import idat.edu.pe.security.JWTUserDetailService;
import idat.edu.pe.security.JwtTokenUtil;

@RestController
@CrossOrigin
public class UsuarioClienteController {
	
	
	@Autowired
	private JWTUserDetailService jWTUserDetailService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(method = RequestMethod.POST, path = "/crearToken")
	public ResponseEntity<?> crearToken(@RequestBody UsuarioClienteRequestDTO request){
		
		UserDetails userDetail = jWTUserDetailService.loadUserByUsername(request.getUsuario());

		return ResponseEntity.ok(new UsuarioClienteDTOResponse(jwtTokenUtil.generateToken(userDetail.getUsername())));
	}
	

	
	
}
