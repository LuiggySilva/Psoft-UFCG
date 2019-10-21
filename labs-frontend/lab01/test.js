let assert = require('assert');
let disciplina = require('./scoord').disciplina;
let turma = require('./scoord').turma;
let professor = require('./scoord').professor;
let estudante = require('./scoord').estudante;

describe('factory Disciplina', function() {
    let d0;

    before(async () => {
        d0 = disciplina('prog1', 'Programação 1', 4, []);
    })

    it('deve criar disciplinas distintas a cada invocação', function(){
        d0 = disciplina('prog1', 'Programação 1', 4, []);
        d1 = disciplina('prog1', 'Programação 1', 4, []);
        d2 = disciplina('prog1', 'Programação 1', 4, []);
        assert.notEqual(d0, d1);
        assert.notEqual(d0, d2);
        assert.notEqual(d1, d2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal('prog1', d0.id());
        assert.equal('Programação 1', d0.get_nome());
        assert.equal(4, d0.creditos);
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('deve permitir atualização de nome', function(){
        d0.set_nome('Programação de Computadores I')
        assert.equal('prog1', d0.id());
        assert.equal('Programação de Computadores I', d0.get_nome());
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('não deve permitir atualização de id via set_id', function(){
        assert.throws(function () {
            d0.set_id('outro')
        }, TypeError);
        assert.equal('prog1', d0.id());
    });

});

describe('factory Professor', function() {
    let p0;
    let t1;
    let t2;

    before(async () => {
        p0 = professor(123456789, "Jubiscreiton", "jubi@ccc.ufcg.edu.br", "123.456.789-10", "www.acre.com.br/jubi");
        t1 = turma("PSOFT", "4");
        t2 = turma("FMCC2", "2");
    })

    it('deve criar professores distintos a cada invocação', function(){
        p0 = professor(123456789, "Jubiscreiton", "jubi@ccc.ufcg.edu.br", "123.456.789-10", "www.acre.com.br/jubi");
        p1 = professor(123456789, "Jubiscreiton", "jubi@ccc.ufcg.edu.br", "123.456.789-10", "www.acre.com.br/jubi");
        p2 = professor(123456789, "Jubiscreiton", "jubi@ccc.ufcg.edu.br", "123.456.789-10", "www.acre.com.br/jubi");
        assert.notEqual(p0, p1);
        assert.notEqual(p0, p2);
        assert.notEqual(p1, p2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal(p0.get_matricula() , 123456789);
        assert.equal(p0.get_nome() , "Jubiscreiton");
        assert.equal(p0.get_email() , "jubi@ccc.ufcg.edu.br");
        assert.equal(p0.get_cpf() , "123.456.789-10");
        assert.equal(p0.get_fotoURL() ,"www.acre.com.br/jubi");
        assert.deepEqual(p0.get_turmas(), []);
    });

    it('deve permitir atualização do nome', function(){
        p0.set_nome("Jubiscreiton Alfonso");
        assert.equal(p0.get_nome(), "Jubiscreiton Alfonso");
    });

    it('deve permitir a alocacao de turmas', function(){
        p0.aloca_turma(t1);
        p0.aloca_turma(t2);
        assert.deepEqual(p0.get_turmas(), ['PSOFT', 'FMCC2']); 
    });

    it('deve ser possivel recuperar as turmas por periodo', function(){
        assert.deepEqual(p0.turmas("4"), ['PSOFT']); 
    });

});

describe('factory Estudante', function() {
    let e0;
    let t1;
    let t2;

    before(async () => {
        e0 = estudante(123456, "individuo1", "indi1@ccc.ufcg.edu.br", "1.2.3-4", "google.com");
        t1 = turma("PSOFT", "4");
        t2 = turma("FMCC2", "2");
    })

    it('deve criar estudantes distintos a cada invocação', function(){
        e0 = estudante(123456, "individuo1", "indi1@ccc.ufcg.edu.br", "1.2.3-4", "google.com");
        e1 = estudante(123456, "individuo1", "indi1@ccc.ufcg.edu.br", "1.2.3-4", "google.com");
        e2 = estudante(123456, "individuo1", "indi1@ccc.ufcg.edu.br", "1.2.3-4", "google.com");
        assert.notEqual(e0, e1);
        assert.notEqual(e0, e2);
        assert.notEqual(e1, e2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal(e0.get_nome(), "individuo1");
        assert.equal(e0.get_email(), "indi1@ccc.ufcg.edu.br");
        assert.equal(e0.get_matricula(), 123456);
        assert.equal(e0.get_cpf(), "1.2.3-4");
        assert.equal(e0.get_fotoURL(), "google.com");
        assert.deepEqual(e0.get_turmas(), []);
    });

    it('deve permitir atualização do nome', function(){
        e0.set_nome("individuo10");
        assert.equal(e0.get_nome(), "individuo10");
    });

    it('deve permitir a matricula em turmas', function(){
        assert.deepEqual(e0.get_turmas(), []);
        e0.matricula(t1);
        e0.matricula(t2);
        assert.deepEqual(e0.get_turmas(), ['PSOFT', 'FMCC2']);
    });

});

describe('factory Turma', function() {
  let t0;
  let p0;
  let p1;
  let e0;

    before(async () => {
        t0 = turma("PSOFT", "4");
        p0 = professor(123456789, "Jubiscreiton", "jubi@ccc.ufcg.edu.br", "123.456.789-10", "www.acre.com.br/jubi");
        p1 = professor(987654321, "Josicreiton", "josi@ccc.ufcg.edu.br", "923.456.789-10", "www.acre.com.br/jubi");
        e0 = estudante(123456, "individuo1", "indi1@ccc.ufcg.edu.br", "1.2.3-4", "google.com");
        
    })

    it('deve criar estudantes distintos a cada invocação', function(){
        t0 = turma("PSOFT", "4");
        t1 = turma("PSOFT", "4");
        t2 = turma("PSOFT", "4");
        assert.notEqual(t0, t1);
        assert.notEqual(t0, t2);
        assert.notEqual(t1, t2);
    });

    it('deve reter os dados de inicialização', function(){
        t0.set_professor(p0);
        assert.equal(t0.get_professor(), 123456789);
        assert.equal(t0.get_status(), "planejada");
        assert.equal(t0.get_periodo(), "4");
        assert.equal(t0.get_disciplina_ref(), "PSOFT");
        assert.deepEqual(t0.get_alunos(), []);
    });

    it('deve permitir atualização do professor e status', function(){
        t0.set_professor(p1);
        assert.equal(t0.get_professor(), 987654321);
        t0.set_status("ativa");
        assert.equal(t0.get_status(), "ativa");
        t0.set_status("concluída");
        assert.equal(t0.get_status(), "concluída");
        t0.set_status("rasgado");
        assert.equal(t0.get_status(), "concluída");
    });

    it('deve ser possivel matricular alunos', function(){
        let t0 = turma("PSOFT", "4");
        t0.matricular(e0);
        t0.matricular(e0);
        assert.deepEqual(t0.get_alunos(), [123456]);
        t0.desmatricular(e0);
        assert.deepEqual(t0.get_alunos(), []);        
    });

});
