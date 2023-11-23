document.addEventListener('DOMContentLoaded', function() {
    const sideMenu = document.querySelector("aside");
    const menuBtn = document.querySelector("#menu-btn");
    const closeBtn = document.querySelector("#close-btn");
    const themeToggler = document.querySelector(".theme-toggler");
    const pathName = window.location.pathname;
    const action = pathName.split('/').pop();
    const right = document.querySelector(".right");
    const main = document.querySelector("main");
    const productForm = document.getElementById("producto-form");
    const insumoForm = document.getElementById("insumos-form");
    const inventory = document.getElementById("inventory");
    const menu = document.getElementById("menu");
    const filas = document.getElementById('tablaInsumos').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    const filasComida = document.getElementById('tablaComidas').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    const filasPorPagina = 8;
    let paginaActual = 1;
    var botonesEdit = document.querySelectorAll('.editar-btn');
    var botonesSave = document.querySelectorAll('.guardar-btn');

    menuBtn.addEventListener('click', () => {
        sideMenu.style.display = 'block';
    })

    closeBtn.addEventListener('click', () => {
        sideMenu.style.display = 'none';
    })

    themeToggler.addEventListener('click', () => {
        document.body.classList.toggle('dark-theme-variables');

        themeToggler.querySelector('span:nth-child(1)').classList.toggle('active');
        themeToggler.querySelector('span:nth-child(2)').classList.toggle('active');
    })

    function showElement(element) {
        element.style.display = 'block';
    }

    function hideElement(element) {
        element.style.display = 'none';
    }

    // Manejador de clic para el botón "Añadir Producto"

    // Lógica para mostrar elementos según la acción
    switch (action) {
        case "dashboard":
            document.querySelector(".dashboard").classList.add("active");
            showElement(main);
            showElement(right);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(inventory);
            hideElement(menu);
            break;
        case "pqrs":
            document.querySelector(".pqrs").classList.add("active");
            hideElement(main);
            hideElement(right);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(menu);
            break;
        case "inventario":
            document.querySelector(".inventario").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(productForm);
            hideElement(insumoForm);
            showElement(inventory);
            hideElement(menu);
            break;
        case "menu":
            document.querySelector(".menu").classList.add("active");
            hideElement(main);
            showElement(right);
            showElement(menu);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            break;
        case "guardarPromociones":
            document.querySelector(".inventario").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(menu);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(inventory);
            break;
        case "guardarProducto":
            document.querySelector(".addP").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(menu);
            hideElement(inventory);
            showElement(productForm);
            hideElement(insumoForm);
            break;
        case "guardarInsumo":
            document.querySelector(".addI").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(menu);
            hideElement(inventory);
            hideElement(productForm);
            showElement(insumoForm);
            break;
        default:
            hideElement(main);
            hideElement(right);
            hideElement(menu);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            break;
    }

    // Función para cambiar la página
    function cambiarPagina(direccion) {
        // Calcula la nueva página
        paginaActual += direccion;

        // Actualiza la visibilidad de las filas
        actualizarVisibilidadFilas();

        // Actualiza el número de página en el span
        document.getElementById('paginaActual').innerText = paginaActual;
    }

    // Función para actualizar la visibilidad de las filas
    function actualizarVisibilidadFilas() {
        for (let i = 0; i < filas.length; i++) {
            if (i >= (paginaActual - 1) * filasPorPagina && i < paginaActual * filasPorPagina) {
                filas[i].classList.add('visible');
            } else {
                filas[i].classList.remove('visible');
            }
        }

        // Actualiza el estado de los botones de paginación
        document.getElementById('anteriorBtn').disabled = paginaActual === 1;
        document.getElementById('siguienteBtn').disabled = paginaActual === Math.ceil(filas.length / filasPorPagina);
    }

    document.getElementById('anteriorBtn').addEventListener('click', () => cambiarPagina(-1));
    document.getElementById('siguienteBtn').addEventListener('click', () => cambiarPagina(1));
    // Inicializa la visibilidad de las filas
    actualizarVisibilidadFilas();

    function cambiarPaginaComida(direccion) {
        // Calcula la nueva página
        paginaActual += direccion;

        // Actualiza la visibilidad de las filas
        actualizarVisibilidadFilasComida();

        // Actualiza el número de página en el span
        document.getElementById('paginaActualComida').innerText = paginaActual;
    }

// Función para actualizar la visibilidad de las filas de comida
    function actualizarVisibilidadFilasComida() {
        for (let i = 0; i < filasComida.length; i++) {
            if (i >= (paginaActual - 1) * filasPorPagina && i < paginaActual * filasPorPagina) {
                filasComida[i].classList.add('visible');
            } else {
                filasComida[i].classList.remove('visible');
            }
        }

        // Actualiza el estado de los botones de paginación
        document.getElementById('anteriorBtnComida').disabled = paginaActual === 1;
        document.getElementById('siguienteBtnComida').disabled = paginaActual === Math.ceil(filasComida.length / filasPorPagina);
    }

    document.getElementById('anteriorBtnComida').addEventListener('click', () => cambiarPaginaComida(-1));
    document.getElementById('siguienteBtnComida').addEventListener('click', () => cambiarPaginaComida(1));

// Inicializa la visibilidad de las filas de comida
    actualizarVisibilidadFilasComida();

    botonesEdit.forEach(function (btnEdit) {
        btnEdit.addEventListener('click', function () {
            activarEdicion(btnEdit);
        });
    });

    botonesSave.forEach(function (btnSave) {
        btnSave.addEventListener('click', function () {
            guardarEdicion(btnSave);
        });
    });

});

function activarEdicion(btnEdit) {
    var fila = btnEdit.closest('tr');
    var camposEditables = fila.querySelectorAll('.editable');

    camposEditables.forEach(function (campo) {
        campo.contentEditable = 'true';
        campo.classList.add('editing');
    });

    // Mostrar botones de guardar y ocultar botón de editar
    fila.querySelector('.editar-btn').style.display = 'none';
    fila.querySelector('.guardar-btn').style.display = 'inline-block';
}

function guardarEdicion(btnSave) {
    var fila = btnSave.closest('tr');
    var comidaId = fila.dataset.comidaId;
    var camposEditables = fila.querySelectorAll('.editable');

    var datosEditados = {
        comidaId: comidaId,
        nombre: fila.querySelector('.editable[data-nombre-campo="nombre"]').innerText.trim(),
        precio: fila.querySelector('.editable[data-nombre-campo="precio"]').innerText.trim(),
        cantidad: fila.querySelector('.editable[data-nombre-campo="cantidad"]').innerText.trim()
    };

    // Realizar la solicitud POST al servidor
    fetch('/actualizarComida', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(datosEditados),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Datos actualizados:', data);
            // Puedes realizar acciones adicionales después de la actualización
        })
        .catch(error => console.error('Error en la solicitud AJAX:', error));

    // Restaurar el estado de la interfaz
    camposEditables.forEach(function (campo) {
        campo.contentEditable = 'false';
        campo.classList.remove('editing');
    });

    // Mostrar botón de editar y ocultar botón de guardar
    fila.querySelector('.editar-btn').style.display = 'inline-block';
    fila.querySelector('.guardar-btn').style.display = 'none';
}