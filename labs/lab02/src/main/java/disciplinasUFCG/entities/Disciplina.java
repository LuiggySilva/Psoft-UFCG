package disciplinasUFCG.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Disciplina{
	
	@Id
	@GeneratedValue
	private long id;
	private String comentarios;
	private int likes;
	private String nome;
	private double nota;
	
	
	public Disciplina() {
		super();
		this.comentarios = "";
	}
	
	public Disciplina(long id, String nome, double nota) {
		this.nome = nome;
		this.nota = nota;
		this.id = id;
		this.comentarios = "";
		this.likes = 0;
		
	}
	
	public Disciplina( String nome) {
		super();
		this.nome = nome;
		this.comentarios = "";
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentario) {
		if (comentario != null) {
			this.comentarios += System.lineSeparator() + comentario;
		}
	}

	public int getLikes() {
		return this.likes;
	}

	public void setLikes(int like) {
		if(like > 0) {
			this.likes += like;
		}
	}

}
