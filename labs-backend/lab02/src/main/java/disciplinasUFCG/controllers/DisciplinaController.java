package disciplinasUFCG.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import disciplinasUFCG.entities.Comentario;
import disciplinasUFCG.entities.Disciplina;
import disciplinasUFCG.entities.DisciplinaDTO;
import disciplinasUFCG.services.DisciplinaService;

@RestController
public class DisciplinaController {
	
	@Autowired
	DisciplinaService DS;
	
	@RequestMapping("/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") String id) {
		Disciplina res = this.DS.getDisciplina(Long.parseLong(id));
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas")
	public ResponseEntity<Collection<DisciplinaDTO>> getTeste() {
		return new ResponseEntity<Collection<DisciplinaDTO>>(this.DS.getDisciplinas(), HttpStatus.OK); 
	}
	
	@PutMapping("/disciplinas/nota/{id}")
	public ResponseEntity<Disciplina> setDisciplinaNota(@PathVariable("id") String id, @RequestBody Disciplina newNota){ 
		Disciplina res = this.DS.setNota(Long.parseLong(id), newNota.getNota());
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@PutMapping("/disciplinas/likes/{id}")
	public ResponseEntity<Disciplina> setDisciplinaNota(@RequestHeader("Authorization") String header, @PathVariable("id") String id){ 
		Disciplina d = this.DS.addLike(Long.parseLong(id), 1);
		if(d == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(d, HttpStatus.OK);
		}
	}
	
	@PutMapping("/disciplinas/comentarios/{id}")
	public ResponseEntity<Disciplina> setDisciplinaComentario(@PathVariable("id") String id, @RequestBody Comentario comentario){ 
		Disciplina d = this.DS.addComentario(Long.parseLong(id), comentario);
		if(d == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(d, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas/ranking/notas")
	public ResponseEntity<Collection<Disciplina>> getRankingDisciplinasNotas(){
		return new ResponseEntity<Collection<Disciplina>>(this.DS.getRankingBy("NOTA"), HttpStatus.OK);
	}
	
	@RequestMapping("/disciplinas/ranking/likes")
	public ResponseEntity<Collection<Disciplina>> getRankingDisciplinasLikes(){
		return new ResponseEntity<Collection<Disciplina>>(this.DS.getRankingBy("LIKE"), HttpStatus.OK);
	}
}
