document.addEventListener('DOMContentLoaded', function() {
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
    const pathName = window.location.pathname;
    const action = pathName.split('/').pop();
    const main = document.querySelector("main");
    const aside = document.querySelector("aside");
    const instantCard = document.querySelector(".instantCard-container");
    const cuenta = document.querySelector(".cuenta-container");
    const pqrs = document.querySelector(".pqrs-container");


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

    document.getElementById('open-popup').addEventListener('click', function() {
        document.getElementById('popup-overlay').style.display = 'flex';

    });

    document.getElementById('close-popup').addEventListener('click', function() {
        document.getElementById('popup-overlay').style.display = 'none';
    });

    function showElement(element) {
        element.style.display = 'block';
    }

    function hideElement(element) {
        element.style.display = 'none';
    }

    switch (action) {
        case "dashboard":
            document.querySelector(".dashboard").classList.add("active");
            hideElement(instantCard);
            showElement(aside);
            showElement(main);
            hideElement(cuenta);
            hideElement(pqrs);
            break;
        case "instantCard":
            document.querySelector(".instantCard").classList.add("active");
            hideElement(main);
            showElement(aside);
            instantCard.style.display = 'flex';
            hideElement(cuenta);
            hideElement(pqrs);
            break;
        case "historial":
            document.querySelector(".historial").classList.add("active");
            showElement(aside);
            hideElement(main);
            hideElement(instantCard);
            hideElement(cuenta);
            hideElement(pqrs);
            break;
        case "cuenta":
            document.querySelector(".cuenta").classList.add("active");
            showElement(aside);
            cuenta.style.display = 'flex';
            hideElement(main);
            hideElement(instantCard);
            hideElement(pqrs);
            break;
        case "pqrs":
            document.querySelector(".pqrs").classList.add("active");
            showElement(aside);
            showElement(pqrs);
            hideElement(main);
            hideElement(instantCard);
            hideElement(cuenta);
            break;
        default:
            hideElement(aside);
            hideElement(main);
            hideElement(instantCard);
            hideElement(cuenta);
            hideElement(pqrs);
            break;
    }

    toggleSelectors.forEach(function (toggleSelector) {
        toggleSelector.addEventListener('click', function () {
            const relatedSelector = this.nextElementSibling;
            relatedSelector.style.display = relatedSelector.style.display === 'none' ? 'block' : 'none';
        });
    });

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
            event.preventDefault();
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

    document.getElementById('btnPagar').addEventListener('click', function() {
        // Realiza una solicitud al servidor para verificar si hay un pedido pendiente
        fetch('/cliente/verificar-pedido')
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.mensaje === "El carrito está vacio") {

                    alert("El carrito está vacío. Agrega productos antes de continuar.");

                } else {

                    window.location.href = '/cliente/pagos';
                }
            })
            .catch(error => {
                console.error('Error al verificar el carrito:', error);
            });
    });

});

function flipCard() {
    const card = document.querySelector('.card');
    card.classList.toggle('flipped');
}

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

function previewImage() {
    var preview = document.getElementById('preview');
    var fileInput = document.getElementById('profile-pic');
    var file = fileInput.files[0];

    if (file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            preview.innerHTML = '<img src="' + e.target.result + '" alt="Vista previa">';
        };

        reader.readAsDataURL(file);
    } else {
        preview.innerHTML = '';
    }
}

