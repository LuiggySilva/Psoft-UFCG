package disciplinasUFCG.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import disciplinasUFCG.DAOS.DisciplinasDAO;
import disciplinasUFCG.entities.Comentario;
import disciplinasUFCG.entities.Disciplina;
import disciplinasUFCG.util.OrdenaPorLike;
import disciplinasUFCG.util.OrdenaPorNota;

@Service
public class DisciplinaService {
	
	@SuppressWarnings("unused")
	private int disciplinasAdicionadas = 0;
	private DisciplinasDAO<Disciplina, Long> disciplinas;
	
	public DisciplinaService(DisciplinasDAO<Disciplina, Long> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	@PostConstruct
	public void initDisciplinas() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Disciplina>> type = new TypeReference<List<Disciplina>>() {};
		InputStream inputStream = ObjectMapper.class.getResourceAsStream("/dados/disciplinas.json");
		try {
			List<Disciplina> disciplinas = mapper.readValue(inputStream, type);
			if (this.disciplinas.count() == 0) {
				this.disciplinas.saveAll(disciplinas);
			}
			System.out.println("Disciplinas Salvas!");

		} catch (Exception e) {
			System.out.println("EEEEEEERRRRRRRROOOOOOOOO ------------->" + e.getMessage());
		}
	}
	
	public Disciplina addDisciplina(Disciplina d) {
		this.disciplinas.save(d);
		this.disciplinasAdicionadas++;
		return d;
	}
	
	public Disciplina setNota(long id, double nota) {
		Disciplina d = this.getDisciplina(id);
		if(d == null) {
			return null;
		}
		d.setNota(nota);
		this.disciplinas.save(d);
		return d;
	}
	
	public Disciplina addComentario(long id, Comentario c) {
		Disciplina d = this.getDisciplina(id);
		if (d == null) {
			return null;
		}
		d.setComentarios(c.getComentario());
		this.addDisciplina(d);
		return d;
	}
	
	public Disciplina addLike(long id, int like) {
		Disciplina d = this.getDisciplina(id);
		if (d == null) {
			return null;
		}
		d.setLikes(like);
		this.addDisciplina(d);
		return d;
	}
	
	public Disciplina getDisciplina(long id) {
		Optional<Disciplina> d;
		try {
			d = this.disciplinas.findById(id);
		} catch (Exception e) {
			return null;
		}
		return d.get();
	}
	
	public List<Disciplina> getDisciplinas() {
		return this.disciplinas.findAll();
	}
	
	public Disciplina deletaDisciplina(long id) {
		Disciplina d;
		try {
			d = this.disciplinas.findById(id).get();
			this.disciplinas.deleteById(id);
		} catch (Exception e) {
			return null;
		}
		return d;
	}
	
	public Collection<Disciplina> getRankingBy(String ordenacao) {
		List<Disciplina> res = new ArrayList<Disciplina>();
		res = this.disciplinas.findAll();
		if(ordenacao.equals("NOTA")) {
			Collections.sort(res, new OrdenaPorNota());
		}
		else if(ordenacao.equals("LIKE")) {
			Collections.sort(res, new OrdenaPorLike());
		}
		return res;
	}

}
