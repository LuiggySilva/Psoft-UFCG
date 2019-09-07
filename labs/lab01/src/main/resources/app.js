let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas';
//let URL = 'http://localhost:1357/v1/api/disciplinas';
let disciplinas = [];

function removerDisciplina(id) {
    fetch(URL + '/' + id, {
        'method': 'DELETE'
    })
        .then(response => response.json())
        .then(dados => {
            console.log(dados);
            fetch_disciplinas();
        })
}

window.removerDisciplina = removerDisciplina;

function show_disciplina($box) {
    let table = "<table align='center' id='listaDisciplinas'> <tr> <td> <h4>ID:</h4> </td> <td> <h4>Nome:</h4> </td> <td> <h4>Nota:</h4> </td> <td> <h4>Remover</h4> </td> </tr>";

    disciplinas.forEach(element => {
        let removeButton = '<button class="removerDisciplina" onclick = "removerDisciplina('+ element.id +')"> X </button>';
        table += '<tr>'
        table += '<td class="disciplinasTable">' + element.id + '</td>' + '<td class="disciplinasTable">' + element.nome + '</td>' + '<td class="disciplinasTable">' + element.nota + '</td>' + '<td class="disciplinasTable">' + removeButton + '</td>';
        table += '</tr>'
    });
    $box.innerHTML = table;
}

function fetch_disciplinas() {
    fetch(URL)
        .then(response => response.json())
        .then(dados => {
            disciplinas = dados;
            let $box = document.querySelector("#disciplinas");
            $box.innerHTML = '';
            show_disciplina($box);
        })
}

function put_disciplina() {
    let tipoMudancaNota = document.querySelector("#notaEdit");
    let tipoMudancaNome = document.querySelector("#nomeEdit");
    let alteracao = document.querySelector("#alteracao");
    let disciplinaID = document.querySelector("#idDisciplina");
    console.log(alteracao.value);

    if(tipoMudancaNome.checked){
        fetch(URL + '/' + disciplinaID.value + '/nome', {
            'method': 'PUT',
            'body': JSON.stringify({"nome":alteracao.value}),
            'headers': { 'Content-Type': 'application/json' }
        })
            .then(response => response.json())
            .then(dados => {
                fetch_disciplinas();
                tipoMudancaNome.checked = false;
                alteracao.value = '';
                disciplinaID.value = '';
                tipoMudancaNota.checked = false;
                
            })    
    }
    else if(tipoMudancaNota.checked){
        fetch(URL + '/' + disciplinaID.value + '/nota', {
            'method': 'PUT',
            'body': JSON.stringify({"nota":alteracao.value}),
            'headers': { 'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(dados => {
                fetch_disciplinas();
                tipoMudancaNome.checked = false;
                alteracao.value = '';
                disciplinaID.value = '';
                tipoMudancaNota.checked = false;
            })
    }
}

function post_disciplinas() {
    let nome = document.querySelector("#nome");
    let nota = document.querySelector("#nota");

    fetch(URL, {
        'method': 'POST',
        'body': `{"nome": "${nome.value}", "nota": ${nota.value}}`,
        'headers': { 'Content-Type': 'application/json'}
    })
        .then(response => response.json())
        .then(dados => {
            fetch_disciplinas();
            nome.value = '';
            nota.value = '';
        })
}

function init() {
    fetch_disciplinas();
    let cadastrar = document.querySelector("#cadastrarDisciplina");
    cadastrar.addEventListener('click', post_disciplinas);
    let editar = document.querySelector("#editarDisciplina");
    editar.addEventListener('click', put_disciplina);
}

init();