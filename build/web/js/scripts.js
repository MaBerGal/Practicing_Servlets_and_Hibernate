function validarIdentificador() {
    var identificadorInput = document.getElementById('identificador');
    var identificadorValue = identificadorInput.value.trim();

    // Verificar si el campo está vacío
    if (identificadorValue === '') {
        var errorValidacion = document.querySelector('.errorValidacion');
        errorValidacion.innerHTML = ''; // Limpiar el mensaje de error si el campo está vacío
        return true;
    }

    // Expresión regular que permite letras, números, guiones, puntos y guiones bajos
    var expresionRegular = /^[a-zA-Z0-9\/\.\-_]+$/;

    if (!expresionRegular.test(identificadorValue)) {
        var errorValidacion = document.querySelector('.errorValidacion');
        errorValidacion.innerHTML = 'Error de formato. Solo Aa-Zz, 0-9, . _- permitidos.';
        identificadorInput.focus();
        return false;
    } else {
        var errorValidacion = document.querySelector('.errorValidacion');
        errorValidacion.innerHTML = ''; // Limpiar el mensaje de error si la validación es exitosa
    }

    return true;
}

        
         function actualizarContador(elemento, max) {
            var longitud = elemento.value.length;
            var contador = document.getElementById('contador-' + elemento.id);
            
            // Mostrar el contador solo si la longitud es mayor que 0
            contador.style.display = longitud > 0 ? 'inline-block' : 'none';
            
            contador.textContent = longitud + '/' + max;
        }
        
         function mostrarContenido(tabId) {
            // Oculta todos los contenidos de las pestañas
            var tabContents = document.querySelectorAll('.tab-content');
            tabContents.forEach(function(content) {
                content.classList.remove('active');
            });

            // Muestra el contenido de la pestaña seleccionada
            var selectedTabContent = document.getElementById(tabId + '-content');
            if (selectedTabContent) {
                selectedTabContent.classList.add('active');
            }

            // Resalta la pestaña activa
            var tabs = document.querySelectorAll('.tab');
            tabs.forEach(function(tab) {
                tab.classList.remove('active');
            });

            var selectedTab = document.getElementById(tabId);
            if (selectedTab) {
                selectedTab.classList.add('active');
            }
        }
        
function esTeclaNumerica(evt) {
  var charCode = (evt.which) ? evt.which : evt.keyCode;
  var inputValue = evt.target.value + String.fromCharCode(charCode);
  var numericValue = parseFloat(inputValue);

  // Verificar que sea un número, menor de 9999999 y con máximo dos decimales
  if (isNaN(numericValue) || numericValue >= 9999999 || /\.\d{3,}$/.test(inputValue)) {
    return false;
  }

  // Permitir solo números y un punto decimal
  if (charCode > 31 && (charCode != 46 && (charCode < 48 || charCode > 57))) {
    return false;
  }

  return true;
}
