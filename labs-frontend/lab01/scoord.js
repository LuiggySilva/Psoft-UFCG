// https://daltonserey.github.io/psoft/0-exercicios/2-oo_em_javascript/
// https://daltonserey.github.io/psoft/0-exercicios/2-oo_em_javascript/v0/test.js
exports.disciplina = disciplina;
exports.professor = professor;
exports.turma = turma;
exports.estudante = estudante;

function disciplina (abreviacao, nome, cred, requisitos) {
	let _abreviacao = abreviacao;
	let _nome = nome;
	return {
		id : () => _abreviacao,
		get_nome : () => _nome,
		set_nome : function (new_nome) { _nome = new_nome; },
		pre_requisitos : requisitos,
		creditos : cred
	};
}

function professor (matricula, nome, email, cpf, fotoURL) {
	var _matricula = matricula;
	var _nome = nome;
	var _email = email;
	var _cpf = cpf;
	var _fotoURL = fotoURL;
	var _turmas = [];
	return {
		get_matricula : () => _matricula,
		get_nome : () => _nome,
		get_email : () => _email,
		get_cpf : () => _cpf,
		get_fotoURL : () => _fotoURL,
		get_turmas : () => {
			var retorno = [];
			_turmas.forEach((t) => {
				retorno.push(t.get_disciplina_ref());
			})
			return retorno;
		},
		set_nome : function (nome) {
			_nome = nome;
		},
		aloca_turma : function (turma) {
			_turmas.push(turma);
		},
		turmas : function (semestre) {
			var retorno = [];
			_turmas.forEach((t) => {
				if (t.get_periodo() === semestre) {
					retorno.push(t);
				}
			});
			return retorno;
		}
	};
}

function estudante (matricula, nome, email, cpf, fotoURL) {
	var _matricula = matricula;
	var _nome = nome;
	var _email = email;
	var _cpf = cpf;
	var _fotoURL = fotoURL;
	var _turmas = [];
	let aluno = {
		get_matricula : () => _matricula,
		get_nome : () => _nome,
		get_email : () => _email,
		get_cpf : () => _cpf,
		get_fotoURL : () => _fotoURL,
		get_turmas : () => {
			var retorno = [];
			_turmas.forEach((t) => {
				retorno.push(t.get_disciplina_ref());
			})
			return retorno;
		},
		set_nome : function (nome) {
			_nome = nome;
		},
		matricula : function (turma) {
			turma.matricular(aluno);
			_turmas.push(turma);
		},
		turmas : function (semestre) {
			var retorno = [];
			_turmas.forEach((t) => {
				if (t.get_periodo() === semestre) {
					retorno.push(t);
				}
			});
			return retorno;
		}
	};
	return aluno;
}

function turma (disciplina_ref, perido) {
	let _professor = null;
	let _status = "planejada";
	let _periodo = perido;
	let _alunos = [];
	let _disciplina_ref = disciplina_ref;
	return {
		get_professor : () => _professor,
		get_status : () => _status,
		get_periodo : () => _periodo,
		get_alunos : () => {
			var retorno = [];
			_alunos.forEach((a) => {
				retorno.push(a.get_matricula());
			})
			return retorno;
		},
		get_disciplina_ref : () => _disciplina_ref,

		set_professor : function (nome) {
			_professor = nome;
		},
		matricular : function (aluno) {
			if (!_alunos.includes(aluno) && (_status === "planejada" || _status === "ativa")){
				_alunos.push(aluno);
			}
		},
		set_status : function (novo) {
			switch (novo) {
				case "planejada":
					_status = novo;
					break;
				case "ativa":
				 	_status = novo;
				 	break;
				case "concluída":
					_status = novo;
					break;
				default:
					_status = _status;
			}
		},
		desmatricular : function (aluno) {
			if (_alunos.includes(aluno)) {
				var index = _alunos.indexOf(aluno);
				delete _alunos[index];
			}
		}
	};
}
// Testes: Estudante
console.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Testes: Estudante -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
var t2 = turma("LP2", "2");
var e1 = estudante(123456, "individuo1", "indi1@ccc.ufcg.edu.br", "8.8.4.4", "faicebuk.com");
console.log(e1);
console.log("estudante-nome: " + e1.get_nome());
console.log("estudante-email: " + e1.get_email());
console.log("estudante-cpf: " + e1.get_cpf());
console.log("estudante-fotoURL: " + e1.get_fotoURL());
console.log("estudante-turmas:");
console.log(e1.get_turmas());
e1.set_nome("individuo10");
console.log("set-estudante-nome: " + e1.get_nome());
e1.matricula(t2);
console.log("set-estudante-turmas:");
console.log(e1.get_turmas());
console.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=- Fim Testes: Estudante -=-=-=-=-=-=-=-=-=-=-=-=-=-=");

// Testes: Turma
console.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Testes: Turma -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
var e2 = estudante(123, "e2", "e2@ccc.ufcg.edu.br", "1.2.3-4", "google");
var e3 = estudante(321, "e3", "e3@ccc.ufcg.edu.br", "12.22.23-24", "google");
var e4 = estudante(213, "e4", "e4@ccc.ufcg.edu.br", "21.22.23-24", "google");

var t1 = turma("PSOFT", "4");
console.log(t1);

t1.set_professor("Dalton");
console.log("turma-professor: " + t1.get_professor());
console.log("turma-status: " + t1.get_status());
console.log("turma-perido: " + t1.get_periodo());
console.log("turma-ref_disc:" + t1.get_disciplina_ref());
console.log("turma-alunos:");
console.table(t1.get_alunos());
t1.set_professor("Dalton e Raquel");
console.log("set-turma-professor: " + t1.get_professor());
t1.set_status("ativa");
t1.set_status("bagunça");
console.log("set-turma-status: " + t1.get_status());
t1.matricular(e1);
t1.matricular(e2);
t1.matricular(e3);
t1.matricular(e4);
console.log("set-turma-alunos:");
console.table(t1.get_alunos());
t1.desmatricular(e1);
console.log("set-turma-alunos:");
console.table(t1.get_alunos());
console.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=- Fim Testes: Turma -=-=-=-=-=-=-=-=-=-=-=-=-=-=");

// Testes: Professor
console.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Testes: Professor -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
var p1 = professor(123456789, "Jubiscreiton", "jubi@ccc.ufcg.edu.br", "123.456.789-10", "www.acre.com.br/jubi");
console.log(p1);

console.log("professor-matricula: " + p1.get_matricula());
console.log("professor-nome: " + p1.get_nome());
console.log("professor-email: " + p1.get_email());
console.log("professor-cpf: " + p1.get_cpf());
console.log("professor-fotoURL: " + p1.get_fotoURL());
p1.set_nome("Jubiscreiton Alfonso");
console.log("set-professor-nome: " + p1.get_nome());
console.log("professor_turmas:");
console.table(p1.get_turmas());
p1.aloca_turma(t1);
p1.aloca_turma(t2);
console.log("professor-aloca_turmas:");
console.log(p1.get_turmas());
console.log("professor-turmas(4):");
console.log(p1.turmas("4")[0].get_disciplina_ref());
console.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=- Fim Testes: Estudante -=-=-=-=-=-=-=-=-=-=-=-=-=-=");