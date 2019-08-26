package psoft.lab01.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import psoft.lab01.entities.DTODisciplina;
import psoft.lab01.entities.Disciplina;

@Service
public class DisciplinaServices {
	
	private Map<Integer, Disciplina> disciplinas = new HashMap<Integer, Disciplina>();
	private int disciplinasAdicionadas = 0;
	
	public Disciplina addDisciplina(String nome, double nota) {
		this.disciplinas.put(this.disciplinasAdicionadas, new Disciplina(this.disciplinasAdicionadas, nome, nota));
		this.disciplinasAdicionadas += 1;
		return this.getDisciplina(this.disciplinasAdicionadas - 1);
	}
	
	public Collection<Disciplina> addDisciplina(Collection<DTODisciplina> ds) {
		for (DTODisciplina dtoDisciplina : ds) {
			this.addDisciplina(dtoDisciplina.getNome(), dtoDisciplina.getNota());
		}
		return this.disciplinas.values();
	}
	
	public Disciplina getDisciplina(int id) {
		Disciplina d;
		try {
			d = (Disciplina) this.disciplinas.get(id);
		} catch (Exception e) {
			return null;
		}
		return d;
	}
	
	public Collection<Disciplina> getDisciplinas() {
		return disciplinas.values();
	}
	
	public Disciplina deletaDisciplina(int id) {
		Disciplina d;
		try {
			d = (Disciplina) this.disciplinas.get(id);
			this.disciplinas.remove(id);
		} catch (Exception e) {
			return null;
		}
		return d;
	}
	
	public Collection<Disciplina> getRanking() {
		List<Disciplina> res = new ArrayList<Disciplina>();
		res.addAll(this.getDisciplinas());
		Collections.sort(res);
		return res;
	}
	
	public List<Disciplina> getByNota(String relacao, double nota) {
		List<Disciplina> dis = new ArrayList<Disciplina>();
		for (Disciplina d : this.disciplinas.values()) {
			switch(relacao) {
			case "=":
				if(d.getNota() == nota) {
					dis.add(d);
				}
				break;
			case ">":
				if(d.getNota() > nota) {
					dis.add(d);
				}
				break;
			case "<":
				if(d.getNota() < nota) {
					dis.add(d);
				}
				break;
			case ">=":
				if(d.getNota() >= nota) {
					dis.add(d);
				}
				break;
			case "<=":
				if(d.getNota() <= nota) {
					dis.add(d);
				}
				break;
			}
		}
		return dis;
	}
}
