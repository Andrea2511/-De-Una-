const sideMenu = document.querySelector(".sidebar");
const shoppingCart = document.querySelector(".right");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const closeShopBtn = document.querySelector("#closeShop-btn");
const shopBtn = document.querySelector("#shop-btn");
const themeToggler= document.querySelector(".theme-toggler");
const toggleSelectors = document.querySelectorAll('.toggle-selector');
const addProducts = document.querySelectorAll('#crearPedidoBtn');
var subtractButtons = document.querySelectorAll('.amount .subtract');
var addButtons = document.querySelectorAll('.amount .add');
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


addProducts.forEach(function (addProduct) {
    addProduct.addEventListener('click', function (event) {
        console.log("Button clicked:", this);
        if (validateForm()) {
            var index = this.getAttribute("data-index");
            var formId = "form-" + index;

            // Obtén los datos del formulario actual
            var formData = new FormData(document.getElementById(formId));

            // Realiza una solicitud AJAX para enviar los datos al controlador
            fetch("/cliente/carritoCompras", {
                method: "POST",
                body: formData
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Error en la solicitud AJAX. Estado: " + response.status);
                    }
                    return response.json();
                })
                .then(responseData => {
                    // Muestra el mensaje del servidor
                    alert(responseData.mensaje);
                })
                .catch(error => {
                    // Error general
                    console.error("Error al realizar la solicitud AJAX:", error);
                })
                .finally(() => {
                    // Redirige a /cliente/dashboard después de cerrar la alerta
                    window.location.href = '/cliente/dashboard';
                });

            // Evita que el formulario realice su acción predeterminada (la recarga de la página)
            event.preventDefault();
        }
    });
});

subtractButtons.forEach(function (subtractButton) {
    subtractButton.addEventListener('click', function (event) {
        // Obtener el elemento input asociado a este botón
        var input = event.target.parentElement.querySelector('.amount-input');

        // Obtener el valor actual
        var currentValue = parseInt(input.value, 10);

        // Restar 1 si es mayor que 1
        if (currentValue > 1) {
            input.value = currentValue - 1;
        }
    });
});

// Agregar un evento de clic a cada botón de suma
addButtons.forEach(function (addButton) {
    addButton.addEventListener('click', function (event) {
        // Obtener el elemento input asociado a este botón
        var input = event.target.parentElement.querySelector('.amount-input');

        // Obtener el valor actual
        var currentValue = parseInt(input.value, 10);

        // Sumar 1
        input.value = currentValue + 1;
    });
});



