const sideMenu = document.querySelector(".sidebar");
const shoppingCart = document.querySelector(".right");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const closeShopBtn = document.querySelector("#closeShop-btn");
const shopBtn = document.querySelector("#shop-btn");
const themeToggler= document.querySelector(".theme-toggler");

menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
    menuBtn.style.display = 'none';
    closeBtn.style.display = 'block'; // Mostrar el botón de cerrar al abrir el menú
});

closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
    menuBtn.style.display = 'block'; // Mostrar el botón de menú al cerrar el menú
    closeBtn.style.display = 'none'; // Ocultar el botón de cerrar al cerrar el menú
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