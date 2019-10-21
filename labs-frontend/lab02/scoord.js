// https://daltonserey.github.io/psoft/0-exercicios/2-oo_em_javascript/
// https://daltonserey.github.io/psoft/0-exercicios/2-oo_em_javascript/v0/test.js

class Disciplina {

	constructor(abreviacao, nome, cred, requisitos) {
		this.abreviacao = abreviacao;
		this.nome = nome;
		this.cred = cred;
		this.requisitos = requisitos;
	}

	get_nome() {
		return this.nome;
	}

	id() {
		return this.abreviacao;
	}

	pre_requisitos() {
		return this.pre_requisitos;
	}

	set_nome(novo_nome) {
		this.nome = novo_nome;
	}
}

class Turma {
	constructor(disciplina_ref, periodo) {
		this.disciplina_ref = disciplina_ref;
		this.periodo = periodo;
		this.professor = null;
		this.status = "planejada";
		this.alunos = [];

		Object.defineProperty(this, 'status', {
		set: function (valor) { this.status = valor},
		get: function () { return this.status }		
	});
	}

	

	get_disciplina_ref() {
		return this.disciplina_ref;
	}
	get_status() {
		return this.status;
	}
	get_periodo() {
		return this.periodo;
	}
	get_alunos() {
		return this.alunos;
	}

	set_professor(novo_professor) {
		this.professor = novo_professor;
	}

	matricular(aluno) {
		let existe = false;
		this
	}
}

let d = new Disciplina("PSOFT", "Projeto de Software", 4, []);
console.log(d.status);


exports.Disciplina = Disciplina;