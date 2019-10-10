package disciplinasUFCG.controllers;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplinasUFCG.entities.Usuario;
import disciplinasUFCG.services.JWTService;
import disciplinasUFCG.services.UsuarioService;
import disciplinasUFCG.util.UsuarioStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	UsuarioService US;
	@Autowired
	JWTService JWTS;
	
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

		if (US.getUsuario(usuario.getEmail()) == null) {
			throw new ServletException("Usuario nao encontrado!");
		}

		if (US.verificaUsuario(usuario.getEmail(), usuario.getSenha())) {
			throw new ServletException("Senha invalida!");
		}

		String token = JWTS.geraToken(usuario.getEmail());

		return new LoginResponse(token);

	}
	
	@DeleteMapping("/usuarios")
	public ResponseEntity<Usuario> removeUsuario( @RequestHeader("Authorization") String header) {
		try {
			String email = JWTS.getSujeitoDoToken(header);
			if(JWTS.usuarioExiste(header)) {
				return new ResponseEntity<Usuario>(US.removeUsuario(email),HttpStatus.OK);
			}
		}catch(ServletException e){
			return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}
		
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
}
