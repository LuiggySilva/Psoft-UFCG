package disciplinasUFCG.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplinasUFCG.entities.Usuario;
import disciplinasUFCG.services.JWTService;
import disciplinasUFCG.services.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService US;
	@Autowired
	JWTService JWTS;
	
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> addDisciplina(@RequestBody Usuario u) {
		return new ResponseEntity<Usuario>(this.US.addUsuario(u), HttpStatus.OK);
	}

}
