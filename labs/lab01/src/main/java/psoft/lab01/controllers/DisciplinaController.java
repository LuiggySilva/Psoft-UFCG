package psoft.lab01.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psoft.lab01.entities.DTODisciplina;
import psoft.lab01.entities.Disciplina;
import psoft.lab01.services.DisciplinaServices;

@RestController
public class DisciplinaController {
	
	@Autowired
	private DisciplinaServices DS;
	
	@PostMapping("/disciplinas")
	public ResponseEntity<Disciplina> addDisciplina(@RequestBody DTODisciplina ds) {
		return new ResponseEntity<Disciplina>(this.DS.addDisciplina(ds.getNome(), ds.getNota()), HttpStatus.OK);
	}
	
	@PostMapping("/disciplinas/lista")
	public ResponseEntity<Collection<Disciplina>> addDisciplinas(@RequestBody List<DTODisciplina> ds) {
		if(ds.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<Collection<Disciplina>>(this.DS.addDisciplina(ds), HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") String id) {
		Disciplina res = this.DS.getDisciplina(Integer.parseInt(id));
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas/byNota{relacao}/{nota}")
	public ResponseEntity<List<Disciplina>> getDisciplinasByNota(@PathVariable("nota") String nota, @PathVariable("relacao") String relacao) {
		List<Disciplina> res = this.DS.getByNota(relacao, Double.parseDouble(nota));
		if(res == null) {
			return new ResponseEntity<List<Disciplina>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Disciplina>>(res, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas")
	public ResponseEntity<Collection<Disciplina>> getTeste() {
		return new ResponseEntity<Collection<Disciplina>>(this.DS.getDisciplinas(), HttpStatus.OK); 
	}
	
	@PutMapping("/disciplinas/{id}/nome")
	public ResponseEntity<Disciplina> setDisciplinaNome(@PathVariable("id") String id, @RequestBody Disciplina newName){ 
		Disciplina res = this.DS.getDisciplina(Integer.parseInt(id));
		
		
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			res.setNome(newName.getNome());
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@PutMapping("/disciplinas/{id}/nota")
	public ResponseEntity<Disciplina> setDisciplinaNota(@PathVariable("id") String id, @RequestBody Disciplina newNota){ 
		Disciplina res = this.DS.getDisciplina(Integer.parseInt(id));
		
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			res.setNota(newNota.getNota());
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/disciplinas/{id}")
	public ResponseEntity<Disciplina> deleteDisciplina(@PathVariable("id") String id){ 
		Disciplina res = this.DS.deletaDisciplina(Integer.parseInt(id));
		
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas/ranking")
	public ResponseEntity<Collection<Disciplina>> getRankingDisciplinas(){
		return new ResponseEntity<Collection<Disciplina>>(this.DS.getRanking(), HttpStatus.OK);
	}
}
