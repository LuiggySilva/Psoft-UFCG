package disciplinasUFCG.services;

import org.springframework.stereotype.Service;

import disciplinasUFCG.DAOS.UsuariosDAO;
import disciplinasUFCG.entities.Usuario;
import disciplinasUFCG.util.UsuarioStatus;

@Service
public class UsuarioService {
	
	static UsuariosDAO<Usuario, String> usuarios;
	
	public UsuarioService(UsuariosDAO<Usuario, String> u) {
		usuarios = u;
	}
	
	public Usuario addUsuario(Usuario u) {
		usuarios.save(u);
		System.out.println(">>> " + usuarios.findById("luiggy@email.com").get().getNome());
		return u;
	}
	
	public Usuario removeUsuario(String email) {
		Usuario u;
		try {
			u = usuarios.findById(email).get();
			usuarios.deleteById(email);
		} catch (Exception e) {
			return null;
		}
		return u;
	}
	
	public Usuario getUsuario(String email) {
		Usuario u;
		try {
			u = usuarios.findById(email).get();
		} catch (Exception e) {
			return null;
		}
		return u;
	}
	
	public boolean verificaUsuario(String email, String senha) {
		Usuario u = this.getUsuario(email);
		if(u == null) {
			return true;
		}
		else if(!u.getSenha().equals(senha)) {
			return true;
		}
		else {
			return false;
		}
	}
}
