var check = function() {
  if (document.getElementById('password').value ==
    document.getElementById('repassword').value) {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'COINCIDEN';
    document.getElementById('buttonOk').hidden = false;
  } else {
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'Las contraseñas no coinciden';
    document.getElementById('buttonOk').hidden = true;
  }
}