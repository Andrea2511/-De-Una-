document.addEventListener("DOMContentLoaded", function () {
    var backButton = document.querySelector("#btn-back");
    var backButton1 = document.querySelector("#btn-back1");
    var nextButton = document.querySelector("#btn-next");
    var payButton = document.querySelector("#btn-pay");
    var paymentContainer = document.querySelector(".pago-container");
    var deliveryContainer = document.querySelector(".entrega-container");
    var deliveryTab = document.querySelector("#entrega");
    var pagoTab = document.querySelector("#pago");

    backButton.addEventListener("click", function () {
        // Oculta el contenedor de pago
        paymentContainer.style.display = "none";
        deliveryContainer.style.display = "block";
        // Agrega la clase 'active' al elemento de la lista de entrega
        deliveryTab.classList.add("active");
        pagoTab.classList.remove("active");
    });

    backButton1.addEventListener("click", function () {
        window.location.href = "dashboard";
    });

    nextButton.addEventListener("click", function () {
        // Oculta el contenedor de pago
        paymentContainer.style.display = "block";
        deliveryContainer.style.display = "none";

        // Agrega la clase 'active' al elemento de la lista de entrega
        pagoTab.classList.add("active");
        deliveryTab.classList.remove("active");
    });

    payButton.addEventListener("click", function () {
        const confirmacion = confirm("¿Estás seguro de que deseas continuar?");

        if (confirmacion) {

            const xhr = new XMLHttpRequest();
            xhr.open('POST', '/cliente/detallePago', true);
            xhr.setRequestHeader('Content-Type', 'application/json');

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {

                        console.log('Respuesta del servidor:', xhr.responseText);
                        window.location.href = "historial";

                    } else {

                        console.error('Error en la solicitud AJAX:', xhr.statusText);
                    }
                }
            };

            xhr.send();
        } else {

        }
    });

    document.getElementById("flexSwitchCheckChecked").addEventListener("change", function () {
        var accordionContent = document.querySelector("#collapseTwo");

        if (this.checked) {

            document.querySelector(".alert-success").style.display = "none";
            document.querySelector(".alert-warning").style.display = "none";
            document.querySelector(".alert-danger").classList.remove("hidden");

            accordionContent.style.display = "none";
            document.querySelector(".accordion-button").setAttribute("disabled", "true");

        } else {

            document.querySelector(".alert-success").style.display = "block";
            document.querySelector(".alert-warning").style.display = "block";
            document.querySelector(".alert-danger").classList.add("hidden");

            accordionContent.style.display = "block";
            document.querySelector(".accordion-button").removeAttribute("disabled");
        }
    });

    var hoy = new Date();

    // Encuentra el próximo sábado
    var proximoSabado = new Date();
    proximoSabado.setDate(hoy.getDate() + (6 - hoy.getDay()) + 1);

    // Formato ISO 8601 para los valores mínimo y máximo en el selector de fecha y hora
    var fechaActualFormato = hoy.toISOString().slice(0, 16);
    var proximoSabadoFormato = proximoSabado.toISOString().slice(0, 16);

    document.getElementById('fechaRecogida').setAttribute('min', fechaActualFormato);
    document.getElementById('fechaRecogida').setAttribute('max', proximoSabadoFormato);

    document.getElementById('fechaRecogida').addEventListener('input', function () {
        var seleccion = new Date(this.value);
        var hora = seleccion.getHours();

        // Verifica si la hora está dentro del rango de 7 am a 7 pm
        if (hora < 7 || hora > 19) {
            alert('Selecciona una hora entre las 7 am y las 7 pm.');
            this.value = ''; // Limpia el campo si la hora está fuera del rango
        }
    });
});

function capturarDatos() {
    // Obtener el valor del switch
    var deseaDomicilio = document.getElementById('flexSwitchCheckChecked').checked;

    // Obtener los valores de los select
    var fechaRecogida = document.getElementById('fechaRecogida').value;
    console.log(fechaRecogida)

    var datos = {
        deseaDomicilio: deseaDomicilio,
        fechaRecogida: fechaRecogida
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/cliente/datosPedido', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // Manejar la respuesta del servidor si es necesario
                console.log('Respuesta del servidor:', xhr.responseText);
            } else {
                console.error('Error en la solicitud AJAX:', xhr.statusText);
            }
        }
    };

    xhr.send(JSON.stringify(datos));
}