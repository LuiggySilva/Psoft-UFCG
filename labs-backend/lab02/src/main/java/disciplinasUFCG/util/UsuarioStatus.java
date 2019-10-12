package disciplinasUFCG.util;

public enum UsuarioStatus {
	ENCONTRADO(1),
	NAO_ENCONTRADO(2),
	SENHA_INVALIDA(3);
	
	private final int resul;
	
	UsuarioStatus(int valor) {
		this.resul = valor;
	}
	
	public int getResul() {
		return resul;
	}
	
}
