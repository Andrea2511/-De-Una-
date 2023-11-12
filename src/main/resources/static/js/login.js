const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
});

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".sign-in-form").addEventListener("submit", function(event) {
        event.preventDefault(); // Evita que el formulario se envíe normalmente

        // Obtiene los valores de usuario y contraseña
        var username = document.querySelector("input[type='text']").value;
        var password = document.querySelector("input[type='password']").value;

        // Obtiene el elemento de mensaje de error
        var errorMessageElement = document.getElementById("error-message");

        // Limpia el mensaje de error anterior
        errorMessageElement.innerHTML = "";

        // Necesitas hacer una solicitud AJAX al servidor para verificar al usuario.
        // API Fetch.
        fetch("/verificar-usuario", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username: username, password: password })
        })
            .then(response => response.json())
            .then(data => {
                // Maneja la respuesta del servidor
                if (data.success) {
                    console.log("Autenticación exitosa:", data.message);
                    // Verifica si hay información de redirección en la respuesta
                    if (data.redirect) {
                        console.log("Redirigiendo a:", data.redirect);
                        window.location.href = data.redirect;
                    }
                } else {
                    // La autenticación falló, muestra un mensaje de error
                    console.log("Autenticación fallida");
                    errorMessageElement.innerHTML = "Usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.";
                }
            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
                errorMessageElement.innerHTML = "Error en la solicitud. Por favor, inténtalo de nuevo más tarde.";
            });
    });
});

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".sign-up-form").addEventListener("submit", function(event) {
        event.preventDefault(); // Evita que el formulario se envíe normalmente

        // Obtiene los valores de usuario y contraseña
        var firstName = document.querySelector("input[name='firstName']").value;
        var lastName = document.querySelector("input[name='lastName']").value;
        var username = document.querySelector("input[name='username']").value;
        var email = document.querySelector("input[name='email']").value;
        var password = document.querySelector("input[name='password']").value;

        // Obtiene el elemento de mensaje de error
        var errorMessageElement = document.getElementById("error-message1");

        // Limpia el mensaje de error anterior
        errorMessageElement.innerHTML = "";

        // Realiza una solicitud AJAX al servidor para registrar al usuario.
        fetch("/registrar-usuario", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ firstName: firstName, lastName: lastName, username: username, email: email, password: password })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    console.log("Registro exitoso. Redirigiendo a la página de inicio de sesión.");
                    window.location.href = data.redirect;
                } else {
                    console.error(data.error);
                    // Muestra el mensaje de error en el formulario
                    errorMessageElement.innerHTML = data.error;
                }
            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });
    });
});


