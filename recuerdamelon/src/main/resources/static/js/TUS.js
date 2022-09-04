let contador = 0;
window.addEventListener('load', function() {
    document.getElementById("compartir").addEventListener("click", compartir);
});



const compartir = () => {
    // Valores de seleccionados de equipos
    var selected = [];
    for (var option of document.getElementById('team').options) {
        if (option.selected) {
            selected.push(option.value);
        }
    }

    /*TODO: comentarios*/
    console.log(selected);
    console.log({ equipo: selected});
    return { equipo: selected}
}