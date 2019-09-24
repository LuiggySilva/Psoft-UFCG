package disciplinasUFCG.util;

import java.util.Comparator;

import disciplinasUFCG.entities.Disciplina;
import disciplinasUFCG.entities.DisciplinaDTO;

public class OrdenaPorNota implements Comparator<Disciplina>{
	@Override
	public int compare(Disciplina o1, Disciplina o2) {
		return (int) (o2.getNota() - o1.getNota());
	}
}
