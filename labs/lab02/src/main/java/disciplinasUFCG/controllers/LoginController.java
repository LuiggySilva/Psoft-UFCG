package disciplinasUFCG.controllers;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplinasUFCG.entities.Usuario;
import disciplinasUFCG.services.UsuarioService;
import disciplinasUFCG.util.UsuarioStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	UsuarioService US;
	
	private final String TOKEN_KEY = "login do batman";
	private UsuarioService usuariosService;

	public LoginController(UsuarioService usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

		// Recupera o usuario
		Usuario authUsuario = usuariosService.getUsuario(usuario.getEmail());
		UsuarioStatus UserStats = US.verificaUsuario(authUsuario.getEmail(), authUsuario.getSenha());

		// verificacoes
		if (UserStats == UsuarioStatus.NAO_ENCONTRADO) {
			throw new ServletException("Usuario nao encontrado!");
		}

		if (UserStats == UsuarioStatus.SENHA_INVALIDA) {
			throw new ServletException("Senha invalida!");
		}

		String token = Jwts.builder().setSubject(authUsuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();

		return new LoginResponse(token);

	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}
	}
}
