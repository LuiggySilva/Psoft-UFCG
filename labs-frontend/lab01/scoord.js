// https://daltonserey.github.io/psoft/0-exercicios/2-oo_em_javascript/
// https://daltonserey.github.io/psoft/0-exercicios/2-oo_em_javascript/v0/test.js
exports.disciplina = disciplina;

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

function professor () {
	return {};
}

function estudante () {
	return {};
}

/*
 Estudantes só devem ser matriculados se ainda não estiverem e se a turma estiver ainda em status planejada ou ativa
 Deve ainda ter uma property status cujo valor pode ser "planejada", "ativa", "concluída", indicando sua situação atual. Deve ser alterada via métodos set_status(novo) que recebe o novo status. Se o novo status for inválido, não deve ter efeito.
*/

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
		get_alunos : () => _alunos,
		get_disciplina_ref : () => _disciplina_ref,

		matricula_aluno : () => ;
		desmatricular_aluno : () => ;
		set_status : function (new_status) {},
		}
	};
}