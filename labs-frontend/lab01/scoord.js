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
					retorno.push(t.get_disciplina_ref());
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
		get_professor : () => _professor.get_matricula(),
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

		set_professor : function (p) {
			_professor = p;
		},
		matricular : function (aluno) {
			let existe = false;
			_alunos.forEach((a) => {
				if (aluno.get_matricula() === a.get_matricula()){
				    existe = true;
				}	
			})
			if(!existe && (_status === "planejada" || _status === "ativa")){
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