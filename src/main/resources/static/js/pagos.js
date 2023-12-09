document.addEventListener("DOMContentLoaded", function () {
    var backButton = document.querySelector("#btn-back");
    var backButton1 = document.querySelector("#btn-back1");
    var nextButton = document.querySelector("#btn-next");
    var paymentContainer = document.querySelector(".pago-container");
    var deliveryContainer = document.querySelector(".entrega-container");
    var deliveryTab = document.querySelector("#entrega");
    var pagoTab = document.querySelector("#pago");

    backButton.addEventListener("click", function () {
        // Oculta el contenedor de pago
        paymentContainer.style.display = "none";
        deliveryContainer.style.display = "block"
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
});