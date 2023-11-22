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
    let addProductBtn;

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
            break;
        case "pqrs":
            document.querySelector(".pqrs").classList.add("active");
            hideElement(main);
            hideElement(right);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            break;
        case "inventario":
            document.querySelector(".inventario").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(productForm);
            hideElement(insumoForm);
            showElement(inventory);
            break;
        case "menu":
            document.querySelector(".menu").classList.add("active");
            hideElement(main);
            hideElement(right);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            break;
        case "guardarProducto":
            document.querySelector(".addP").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(inventory);
            showElement(productForm);
            hideElement(insumoForm);
            addProductBtn = document.getElementById("add-product");
            break;
        case "guardarInsumo":
            document.querySelector(".addI").classList.add("active");
            hideElement(main);
            showElement(right);
            hideElement(inventory);
            hideElement(productForm);
            showElement(insumoForm);
            break;
        default:
            hideElement(main);
            hideElement(right);
            hideElement(inventory);
            hideElement(productForm);
            hideElement(insumoForm);
            break;
    }

    addProductBtn.addEventListener('click', () =>  {
        // Obtiene los datos del nuevo bloque de entrada de comida
        const nombre = document.querySelector('#producto-form input[name="nombre"]').value;
        const precio = document.querySelector('#producto-form input[name="precio"]').value;
        const cantidad = document.querySelector('#producto-form input[name="cantidad"]').value;
        const tipoComida = document.querySelector('#producto-form select[name="tipoComida"]').value;
        const imagen = document.querySelector('#producto-form input[name="imagen"]').files[0];

        // Crea un objeto FormData y agrega los datos
        const formData = new FormData();
        formData.append('nombre', nombre);
        formData.append('precio', precio);
        formData.append('cantidad', cantidad);
        formData.append('tipoComida', tipoComida);
        formData.append('imagen', imagen);

        // Realiza la solicitud AJAX usando fetch
        fetch('/admin/guardarProducto', {
            method: 'POST',
            body: formData,
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al guardar el producto.');
                }
                // Redirecciona a la página deseada después de guardar
                window.location.href = '/admin/guardarProducto';
            })
            .catch(error => {
                // Maneja errores si es necesario
                console.error(error.message);
            });
    });

});