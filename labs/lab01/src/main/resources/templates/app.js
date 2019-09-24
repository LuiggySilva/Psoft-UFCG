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

function tst(){
    let $table = document.createElement("table");
    $table.id = "disciplinasTable";
    $table.style = "text-align:center";
    let $removeButton = document.createElement('button');
    $removeButton.className = 'removerDisciplina';
    $removeButton.innerHTML = 'X';
    $removeButton.addEventListener('click', _ => {
        removerDisciplina(element.id);
    })

    let $tr1 = document.createElement("tr");
    let $tr2 = document.createElement("tr");
    let $tdID = document.createElement("td");
    let $tdNOME = document.createElement("td");
    let $tdNOTA = document.createElement("td");
    let $tdREMOVER = document.createElement("td");

    let $h41 = document.createElement("h4");
    let $h42 = document.createElement("h4");
    let $h43 = document.createElement("h4");
    let $h44 = document.createElement("h4");

    let $td1 = document.createElement("td");
    let $td2 = document.createElement("td");
    let $td3 = document.createElement("td");
    let $td4 = document.createElement("td");


    $h41.innerHTML = "ID:";
    $h42.innerHTML = "Nome:";
    $h43.innerHTML = "Nota:";
    $h44.innerHTML = "Remover:";


    $td1.appendChild($h41);
    $td2.appendChild($h42);
    $td3.appendChild($h43);
    $td4.appendChild($h44);
    
    $tr1.appendChild($td1);
    $tr1.appendChild($td2);
    $tr1.appendChild($td3);
    $tr1.appendChild($td4);

    $tdID.className = "disciplinasTable";
    $tdNOME.className = "disciplinasTable";
    $tdNOTA.className = "disciplinasTable";
    $tdREMOVER.className = "disciplinasTable";
    $tdREMOVER.appendChild($removeButton);
    
    $tr2.appendChild($tdID);
    $tr2.appendChild($tdNOME);
    $tr2.appendChild($tdNOTA);
    $tr2.appendChild($tdREMOVER);
    $table.appendChild($tr1);
    $table.appendChild($tr2);
    document.querySelector("#tst").appendChild($table);
}

function show_disciplina($box) {
    let table = "<table align='center' id='listaDisciplinas'> <tr> <td> <h4>ID:</h4> </td> <td> <h4>Nome:</h4> </td> <td> <h4>Nota:</h4> </td> <td> <h4>Remover:</h4> </td> </tr>";

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