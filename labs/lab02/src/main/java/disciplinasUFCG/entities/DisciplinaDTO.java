package disciplinasUFCG.entities;

public class DisciplinaDTO {
	
	private long ID;
	private String nome;

	public DisciplinaDTO(long id, String nome) {
		this.ID = id;
		this.nome = nome;
	}

	public long getId() {
		return this.ID;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
