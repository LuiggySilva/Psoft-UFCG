package psoft.lab01.controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import psoft.lab01.entities.DTODisciplina;
import psoft.lab01.entities.Disciplina;
import psoft.lab01.services.DisciplinaServices;

@RestController
public class DisciplinaController {
	
	@Autowired
	private DisciplinaServices DS;
	
	
	@PostMapping("/disciplinas")
	@CrossOrigin
	public ResponseEntity<Disciplina> addDisciplina(@RequestBody DTODisciplina ds) {
		return new ResponseEntity<Disciplina>(this.DS.addDisciplina(ds.getNome(), ds.getNota()), HttpStatus.OK);
	}
	
	@RequestMapping("/disciplinas/{id}")
	@CrossOrigin
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") String id) {
		Disciplina res = this.DS.getDisciplina(Integer.parseInt(id));
		if(res == null) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Disciplina>(res, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/disciplinas")
	@CrossOrigin
	public ResponseEntity<Collection<Disciplina>> getTeste() {
		return new ResponseEntity<Collection<Disciplina>>(this.DS.getDisciplinas(), HttpStatus.OK); 
	}
	
	@PutMapping("/disciplinas/{id}/nome")
	@CrossOrigin
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
	@CrossOrigin
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
	@CrossOrigin
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
	@CrossOrigin
	public ResponseEntity<Collection<Disciplina>> getRankingDisciplinas(){
		return new ResponseEntity<Collection<Disciplina>>(this.DS.getRanking(), HttpStatus.OK);
	}

	@Configuration
	public class MyConfiguration {

	    @SuppressWarnings("deprecation")
		@Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	            }
	        };
	    }
	}
}

