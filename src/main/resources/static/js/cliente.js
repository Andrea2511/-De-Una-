document.addEventListener('DOMContentLoaded', function() {
    var btnProfile = document.querySelector('.sidebar-menu-profile');
    var perfilFormulario = document.getElementById('perfilFormulario');
    var dashboard = document.getElementById('dashboard');
    var btnInicio = document.querySelector('.sidebar-menu-inicio');

    // Verificar si el botón de perfil y el formulario existen
    if ( dashboard && btnInicio) {
       /** btnProfile.addEventListener('click', function() {
            dashboard.classList.add('hidden');
            // Mostrar el formulario eliminando la clase 'hidden'
            if (perfilFormulario.style.display === 'none' || perfilFormulario.style.display === '') {
                perfilFormulario.style.display = 'block';
            } else {
                perfilFormulario.style.display = 'none';
            }
        });**/

        btnInicio.addEventListener('click', function() {
            // Ocultar el formulario y mostrar el dashboard nuevamente
            perfilFormulario.classList.add('hidden');
            dashboard.classList.remove('hidden');
        });
    } else {
        console.error('No se encontró el botón de perfil o el formulario');
    }
});

$(document).ready(function() {
    $('.card-link').click(function(e) {
        e.preventDefault(); // Evita la acción predeterminada del enlace (evitar ir al inicio de la página)

        // Encuentra la información adicional de la tarjeta clicada
        var additionalInfo = $(this).find('.additional-info');

        // Oculta todas las informaciones adicionales
        $('.additional-info').not(additionalInfo).hide();

        // Muestra la información adicional de la tarjeta clicada
        additionalInfo.toggle();
    });
});


