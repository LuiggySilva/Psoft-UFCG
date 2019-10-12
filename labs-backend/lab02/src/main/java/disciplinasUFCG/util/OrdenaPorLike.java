package disciplinasUFCG.util;

import java.util.Comparator;

import disciplinasUFCG.entities.Disciplina;
import disciplinasUFCG.entities.DisciplinaDTO;

public class OrdenaPorLike implements Comparator<Disciplina> {

	@Override
	public int compare(Disciplina o1, Disciplina o2) {
		return (int) ((((Disciplina) o2).getLikes()) - (((Disciplina) o1).getLikes()));
	}
}
