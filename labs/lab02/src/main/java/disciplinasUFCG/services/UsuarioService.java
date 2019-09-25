package disciplinasUFCG.services;

import disciplinasUFCG.DAOS.UsuariosDAO;
import disciplinasUFCG.entities.Usuario;
import disciplinasUFCG.util.UsuarioStatus;

public class UsuarioService {
	
	UsuariosDAO<Usuario, String> usuarios;
	
	public UsuarioService(UsuariosDAO<Usuario, String> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Usuario addUsuario(Usuario u) {
		this.usuarios.save(u);
		return u;
	}
	
	public Usuario removeUsuario(String email) {
		Usuario u;
		try {
			u = this.usuarios.findById(email).get();
			this.usuarios.deleteById(email);
		} catch (Exception e) {
			return null;
		}
		return u;
	}
	
	public Usuario getUsuario(String email) {
		Usuario u;
		try {
			u = this.usuarios.findById(email).get();
		} catch (Exception e) {
			return null;
		}
		return u;
	}
	
	public UsuarioStatus verificaUsuario(String email, String senha) {
		Usuario u = this.getUsuario(email);
		if(u == null) {
			return UsuarioStatus.NAO_ENCONTRADO;
		}
		else if(!u.getSenha().equals(senha)) {
			return UsuarioStatus.SENHA_INVALIDA;
		}
		else {
			return UsuarioStatus.ENCONTRADO;
		}
	}
}
