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
    const promoForm = document.getElementById("promo");
    const filas = document.getElementById('tablaInsumos').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    const filasComida = document.getElementById('tablaComidas').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    const filasPorPagina = 8;
    let paginaActual = 1;
    var botonesEdit = document.querySelectorAll('.editar-btn');
    var botonesSave = document.querySelectorAll('.guardar-btn');
    var botonesDelete = document.querySelectorAll('.borrar-btn');
    var botonesEditI = document.querySelectorAll('.editar-btn-I');
    var botonesSaveI = document.querySelectorAll('.guardar-btnI');
    var botonesDeleteI = document.querySelectorAll('.borrar-btn-I');

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
            hideElement(promoForm);


            break;
        case "pqrs":
            document.querySelector(".pqrs").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(menu);
            hideElement(promoForm);
            break;
        case "inventario":
            document.querySelector(".inventario").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(productForm);
            hideElement(insumoForm);
            showElement(inventory);
            hideElement(menu);
            hideElement(promoForm);
            break;
        case "menu":
            document.querySelector(".menu").classList.add("active");
            hideElement(main);
            showElement(right);
            showElement(menu);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(promoForm);

            break;
        case "guardarPromociones":
            document.querySelector(".promo").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(menu);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(inventory);
            showElement(promoForm);

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
            hideElement(promoForm);

            break;
        case "guardarInsumo":
            document.querySelector(".addI").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(menu);
            hideElement(inventory);
            hideElement(productForm);
            showElement(insumoForm);
            hideElement(promoForm);

            break;
        default:
            hideElement(main);
            hideElement(right);
            hideElement(menu);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            hideElement(promoForm);

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

    botonesDelete.forEach(function (btnDel) {
        btnDel.addEventListener('click', function () {
            // Obtener el data-comida-id de la fila padre del botón

            var fila = btnDel.closest('tr');
            console.log('fila', fila);

            if (fila) {
                var comidaId = fila.getAttribute('data-comida-id');
                var tbody = fila.parentNode;

                // Verificar si el elemento padre (tbody) existe antes de intentar eliminar la fila
                if (tbody) {
                    tbody.removeChild(fila);
                    // Llamar a la función eliminarComida con el comidaId
                    eliminarComida(comidaId);
                } else {
                    console.error('El elemento padre (tbody) es nulo.');
                }
            } else {
                console.error('No se encontró la fila con data-comida-id.');
            }
        });
    });

    botonesEditI.forEach(function (btnEdit) {
        btnEdit.addEventListener('click', function () {
            activarEdicionInsumo(btnEdit);
        });
    });

    botonesSaveI.forEach(function (btnSave) {
        btnSave.addEventListener('click', function () {
            guardarEdicionI(btnSave);
        });
    });

    botonesDeleteI.forEach(function (btnDel) {
        btnDel.addEventListener('click', function () {
            // Obtener el data-comida-id de la fila padre del botón

            var fila = btnDel.closest('tr');
            console.log('fila', fila);

            if (fila) {
                var insumoId = fila.getAttribute('data-insumo-id');
                var tbody = fila.parentNode;

                // Verificar si el elemento padre (tbody) existe antes de intentar eliminar la fila
                if (tbody) {
                    tbody.removeChild(fila);
                    // Llamar a la función eliminarComida con el comidaId
                    eliminarComidaI(insumoId);
                } else {
                    console.error('El elemento padre (tbody) es nulo.');
                }
            } else {
                console.error('No se encontró la fila con data-insumo-id.');
            }
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

    console.log('id', datosEditados.comidaId);
    console.log('Cantidad', datosEditados.cantidad);

    // Realizar la solicitud POST al servidor
    fetch('/admin/actualizarComida', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(datosEditados),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Datos actualizados:', data);
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

function eliminarComida(comidaId) {
    // Encontrar la fila
    console.log('comidaId:', comidaId);
    var fila = document.querySelector(`tr[data-comida-id="${comidaId}"]`);

    // Verificar si la fila se encuentra
    if (fila) {
        // Agregar la clase de ocultar
        fila.classList.add("oculto");

        // Esperar a que termine la transición antes de eliminar la fila
        fila.addEventListener("transitionend", function() {
            // Eliminar la fila después de que termine la transición
            var tbody = fila.parentNode;
            tbody.removeChild(fila);

            // Realizar la solicitud AJAX para eliminar la entrada en la base de datos
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", `/admin/eliminarComida`, true);
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.onload = function () {
                if (xhr.status === 200) {
                    // Manejar la respuesta del servidor si es necesario
                    console.log(xhr.responseText);
                } else {
                    // Manejar errores si es necesario
                    console.error(xhr.statusText);
                }
            };

            xhr.onerror = function () {
                // Manejar errores de red si es necesario
                console.error("Error de red al intentar realizar la solicitud.");
            };

            xhr.send();
        });
    } else {
        // Manejar el caso en que la fila no se encontró
        console.error(`No se encontró la fila con data-comida-id="${comidaId}"`);
    }
}

function activarEdicionInsumo(btnEdit) {
    var fila = btnEdit.closest('tr');
    var camposEditables = fila.querySelectorAll('.editable');

    camposEditables.forEach(function (campo) {
        campo.contentEditable = 'true';
        campo.classList.add('editing');
    });

    // Mostrar botones de guardar y ocultar botón de editar
    fila.querySelector('.editar-btn-I').style.display = 'none';
    fila.querySelector('.guardar-btnI').style.display = 'inline-block';
}

function guardarEdicionI(btnSave) {
    var fila = btnSave.closest('tr');
    var insumoId = fila.dataset.insumoId;
    var camposEditables = fila.querySelectorAll('.editable');

    var datosEditados = {
        insumoId: insumoId,
        nombre: fila.querySelector('.editable[data-nombre-campo="nombre"]').innerText.trim(),
        cantidad: fila.querySelector('.editable[data-nombre-campo="cantidad"]').innerText.trim(),
        precio: fila.querySelector('.editable[data-nombre-campo="precio"]').innerText.trim(),
        fecha: fila.querySelector('.editable[data-nombre-campo="fecha"]').innerText.trim()
    };

    // Realizar la solicitud POST al servidor
    fetch('/admin/actualizarInsumo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(datosEditados),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Datos actualizados:', data);
        })
        .catch(error => console.error('Error en la solicitud AJAX:', error));

    // Restaurar el estado de la interfaz
    camposEditables.forEach(function (campo) {
        campo.contentEditable = 'false';
        campo.classList.remove('editing');
    });

    // Mostrar botón de editar y ocultar botón de guardar
    fila.querySelector('.editar-btn-I').style.display = 'inline-block';
    fila.querySelector('.guardar-btnI').style.display = 'none';
}

function eliminarComidaI(insumoId) {
    // Encontrar la fila
    var fila = document.querySelector(`tr[data-insumo-id="${insumoId}"]`);

    // Verificar si la fila se encuentra
    if (fila) {
        // Agregar la clase de ocultar
        fila.classList.add("oculto");

        // Esperar a que termine la transición antes de eliminar la fila
        fila.addEventListener("transitionend", function() {
            // Eliminar la fila después de que termine la transición
            var tbody = fila.parentNode;
            tbody.removeChild(fila);

            // Realizar la solicitud AJAX para eliminar la entrada en la base de datos
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", `/admin/eliminarInsumo`, true);
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.onload = function () {
                if (xhr.status === 200) {
                    // Manejar la respuesta del servidor si es necesario
                    console.log(xhr.responseText);
                } else {
                    // Manejar errores si es necesario
                    console.error(xhr.statusText);
                }
            };

            xhr.onerror = function () {
                // Manejar errores de red si es necesario
                console.error("Error de red al intentar realizar la solicitud.");
            };

            xhr.send();
        });
    } else {
        // Manejar el caso en que la fila no se encontró
        console.error(`No se encontró la fila con data-insumo-id="${insumoId}"`);
    }
}
