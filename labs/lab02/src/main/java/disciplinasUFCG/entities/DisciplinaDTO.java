package disciplinasUFCG.entities;

public class DisciplinaDTO {
	
	private long ID;
	private String nome;
	private double nota;

	public DisciplinaDTO(long id, String nome, double nota) {
		this.ID = id;
		this.nome = nome;
		this.nota = nota;
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

	public double getNota() {
		return this.nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
}
