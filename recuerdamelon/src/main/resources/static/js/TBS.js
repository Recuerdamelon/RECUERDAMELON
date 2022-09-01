let contador = 0;
window.addEventListener('load', function() {
    document.getElementById("tareaadd").addEventListener("click", createTarea);
    document.getElementById("compartir").addEventListener("click", compartir);
    createTarea();
    createTarea();
    createTarea();
});

const addTarea = () => {
    contador++;
    return `<input class="iresultados" type="datetime-local" name="inicio" id="inicio_${contador}">
    <input class="iresultados" type="datetime-local" name="fin" id="fin_${contador}">
    <textarea name="tarea" id="tarea_${contador}" cols="100" rows="1" placeholder="Me cago en todo"></textarea>`;
}

const createTarea = () => {
    let div = document.createElement('div');
    div.id = 'tarea';
    div.innerHTML = addTarea();
    document.getElementById("resultados").appendChild(div);
}

const compartir = () => {
    // Valores de seleccionados de equipos
    var selected = [];
    for (var option of document.getElementById('team').options) {
        if (option.selected) {
            selected.push(option.value);
        }
    }
    console.log(selected);
    // Recorrer tareas y añadir a un array
    const tareas = document.querySelectorAll("#tarea");
    console.log(tareas);
    for (let elem of tareas) {
        console.log(elem);
    }
    console.log({ equipo: selected, titulo: document.getElementById('titulo').value });
    return { equipo: selected, titulo: document.getElementById('titulo').value }
}