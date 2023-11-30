const sideMenu = document.querySelector(".sidebar");
const shoppingCart = document.querySelector(".right");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const closeShopBtn = document.querySelector("#closeShop-btn");
const shopBtn = document.querySelector("#shop-btn");
const themeToggler= document.querySelector(".theme-toggler");
const toggleSelectors = document.querySelectorAll('.toggle-selector');
var dashboardCards = document.querySelectorAll('.dashboard-card');


menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
    menuBtn.style.display = 'none';
    closeBtn.style.display = 'block';
});

closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
    menuBtn.style.display = 'block';
    closeBtn.style.display = 'none';
});

shopBtn.addEventListener('click', () =>{
    shoppingCart.style.display = 'block';
})

closeShopBtn.addEventListener('click', () =>{
    shoppingCart.style.display = 'none';
})

themeToggler.addEventListener('click', () =>{
    document.body.classList.toggle('dark-theme-variables');

    themeToggler.querySelector('span:nth-child(1)').classList.toggle('active');
    themeToggler.querySelector('span:nth-child(2)').classList.toggle('active');
})

function mostrarPopup(index) {
    // Oculta todos los popups
    var popups = document.querySelectorAll('.popup');
    popups.forEach(function (popup) {
        popup.style.display = 'none';
    });

    // Muestra el popup específico
    var popupToShow = document.getElementById('popup-' + index);
    if (popupToShow) {
        popupToShow.style.display = 'block';
    }
}

function cerrarPopup(event, index) {
    // Evita la propagación del evento para que no afecte al dashboard-card
    event.stopPropagation();

    var popupToClose = document.getElementById('popup-' + index);
    if (popupToClose) {
        popupToClose.style.display = 'none';
    }
}

toggleSelectors.forEach(function (toggleSelector) {
    toggleSelector.addEventListener('click', function () {
        const relatedSelector = this.nextElementSibling;
        relatedSelector.style.display = relatedSelector.style.display === 'none' ? 'block' : 'none';
    });
});

function validateForm() {
    const ingredientCheckboxes = document.querySelectorAll('input[name="ingredientes"]:checked');
    const drinkRadios = document.querySelectorAll('input[name="bebidas"]:checked');

    if (ingredientCheckboxes.length === 0) {
        alert('Selecciona Al Menos Un Ingrediente.');
        return false;
    }

    if (drinkRadios.length === 0) {
        alert('Selecciona Una Bebida.');
        return false;
    }

    return true;
}

document.getElementById('crearPedidoBtn').addEventListener('click', function () {
    if (validateForm()) {
        var index = this.getAttribute("data-index");
        var formId = "form-" + index;

        // Obtén los datos del formulario actual
        var formData = new FormData(document.getElementById(formId));

        // Realiza una solicitud AJAX para enviar los datos al controlador
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/cliente/carritoCompras", true);

        // Configura el manejo de la respuesta
        xhttp.onload = function () {
            if (xhttp.status >= 200 && xhttp.status < 300) {
                // Éxito: Maneja la respuesta del servidor
                var response = JSON.parse(xhttp.responseText);

                // Muestra el mensaje en la interfaz (puedes adaptar esto según tu estructura HTML)
                alert(response.mensaje);
            } else {
                // Error en la solicitud AJAX
                alert("Error en la solicitud AJAX");
            }
        };

        // Manejo de errores de red o CORS
        xhttp.onerror = function () {
            alert("Error de red o CORS al realizar la solicitud AJAX");
        };

        // Realiza la solicitud AJAX con los datos del formulario
        xhttp.send(formData);
    }
});



