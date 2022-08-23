var check = function() {
  if (document.getElementById('password').value ==
    document.getElementById('repassword').value) {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'Las contraseñas COINCIDEN';
    document.getElementById('buttonOk').disabled = false;



  } else {
    document.getElementById('message').style.color = 'white';
    document.getElementById('message').innerHTML = 'Las contraseñas no coinciden';
    document.getElementById('buttonOk').disabled = true;
    document.getElementById('buttonOk').class="disabled";
  }
}