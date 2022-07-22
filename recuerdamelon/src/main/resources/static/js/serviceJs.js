console.log("Hola Melon!!");

//------------------ Search Task Controllers --------------------- //
const navbarAutocomplete = document.querySelector('#navbar-search-autocomplete');
const navbarData = ['Search all', 'Work', 'Bussines', 'Events', 'Name user']; //Name user: se actualiza la lista los nombres d usuarios con tareas en común
const navbarDataFilter = (value) => {
  return navbarData.filter((item) => {
    return item.toLowerCase().startsWith(value.toLowerCase());
  });
};

new mdb.Autocomplete(navbarAutocomplete, {
  filter: navbarDataFilter,
});

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  div = document.getElementById("myDropdown");
  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}


// -------- POPUP CREAR TASK ---------------// 
 
function openNewTask(){
  document.getElementById("formNewTask").style.display ="block";
}

function cancelNewTask(){
  document.getElementById("formNewTask").style.display ="none";
}

function acceptNewTask(){
  document.getElementById("formNewTask").style.display ="none";
}


// -------- CONTADOR CHAR TITTLES CREAR TASK
// https://codepen.io/AngelKrak/pen/vLQvMe
//Creamos la Funcion
function contCharTittle() {
  $("#numCharTt").val($("#tittle-task1").val().length + " /100"); //Detectamos los Caracteres del Input
  $("#numCharTt").addClass('mui--is-not-empty'); //Agregamos la Clase de Mui para decir que el input no esta vacio y que suba el Texto del Label(Como cuando haces Focus)
} //Aqui termina la Funcion



// -------- CONTADOR CHAR TITTLES CREAR TASK
// -------- AUX

var inputs = "input[maxlength], textarea[maxlength]";
  $(document).on('keyup', "[maxlength]", function (e) {
    var este = $(this),
      maxlength = este.attr('maxlength'),
      maxlengthint = parseInt(maxlength),
      textoActual = este.val(),
      currentCharacters = este.val().length;
      remainingCharacters = maxlengthint - currentCharacters,
      espan = este.prev('label').find('span');      
      // Detectamos si es IE9 y si hemos llegado al final, convertir el -1 en 0 - bug ie9 porq. no coge directamente el atributo 'maxlength' de HTML5
      if (document.addEventListener && !window.requestAnimationFrame) {
        if (remainingCharacters <= -1) {
          remainingCharacters = 0;            
        }
      }
      espan.html(remainingCharacters);
      if (!!maxlength) {
        var texto = este.val(); 
        if (texto.length >= maxlength) {
          este.removeClass().addClass("borderojo");
          este.val(text.substring(0, maxlength));
          e.preventDefault();
        }
        else if (texto.length < maxlength) {
          este.removeClass().addClass("bordegris");
        } 
      } 
    });