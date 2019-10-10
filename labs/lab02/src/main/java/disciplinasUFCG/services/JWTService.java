package disciplinasUFCG.services;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import disciplinasUFCG.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JWTService {
	public final String TOKEN_KEY = "login do batman";
	private UsuarioService UsuarioService;

	public JWTService(UsuarioService usuariosService) {
		super();
		this.UsuarioService = usuariosService;
	}
	
	public String geraToken(String email) {
		return Jwts.builder().setSubject(email)
		.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
		.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();
	}

	public boolean usuarioExiste(String authorizationHeader) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		return UsuarioService.getUsuario(subject) != null;
	}
	
	public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		Usuario optUsuario = UsuarioService.getUsuario(subject);
		return optUsuario != null && optUsuario.getEmail().equals(email);
	}

	public String getSujeitoDoToken(String authorizationHeader) throws ServletException {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(disciplinasUFCG.util.Filtro.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey("login do batman").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}
}
