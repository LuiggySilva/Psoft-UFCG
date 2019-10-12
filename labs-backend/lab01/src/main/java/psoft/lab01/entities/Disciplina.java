package psoft.lab01.entities;

import java.util.Comparator;

public class Disciplina extends DTODisciplina implements Comparable<Disciplina> {

	private int ID;
	public Disciplina(int id, String nome, double nota) {
		super(nome, nota);
		this.ID = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	public int getID() {
		return ID;
	}

	@Override
	public int compareTo(Disciplina o) {
		return (int) (o.getNota() - this.getNota());
	}

}
