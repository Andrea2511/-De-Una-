package co.edu.escuelaing.cvds.project;

import co.edu.escuelaing.cvds.project.model.Categoria;
import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.repository.SessionRepository;
import co.edu.escuelaing.cvds.project.repository.UserRepository;
import co.edu.escuelaing.cvds.project.repository.PromocionRepository;
import co.edu.escuelaing.cvds.project.service.UserService;
import co.edu.escuelaing.cvds.project.service.EncriptarService;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import co.edu.escuelaing.cvds.project.service.InsumoService;
import co.edu.escuelaing.cvds.project.service.PromocionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.model.TipoInsumos;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.security.NoSuchAlgorithmException;
import co.edu.escuelaing.cvds.project.model.Rol;
import co.edu.escuelaing.cvds.project.model.User;
import org.mockito.junit.MockitoJUnitRunner;
import co.edu.escuelaing.cvds.project.model.*;
import java.time.LocalDateTime;


@RunWith(MockitoJUnitRunner.class)
class ProjectApplicationTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EncriptarService encriptarService;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private ComidaRepository comidaRepository;

    @InjectMocks
    private ComidaService comidaService;

    @Mock
    private InsumoRepository insumoRepository;

    @InjectMocks
    private InsumoService insumoService;

    @InjectMocks
    private PromocionService promocionService;

    @Mock
    private PromocionRepository promocionRepository;

    @Mock
    private DetalleComidaInsumo detalleComidaInsumo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //TEST DE USER
    @Test
    void login_ValidCredentials_ReturnsRole() throws NoSuchAlgorithmException {
        String username = "testUser";
        String password = "testPassword";
        User expectedUser = new User();
        expectedUser.setRol(Rol.ADMINISTRADOR);
        expectedUser.setPassword(password); // Establecer una contraseña válida
        // Agrega el usuario esperado al resultado del mock
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);
        // Simula que las contraseñas coinciden
        when(encriptarService.encriptar(password)).thenReturn(password);
        String result = userService.login(username, password);

        assertEquals(expectedUser.getRol().toString(), result);
        // Verifica que los métodos del repositorio y del servicio de encriptación fueron llamados
        verify(userRepository, times(1)).findByUsername(username);
        //verify(encriptarService, times(1)).encriptar(password);
    }

    @Test
    void login_InvalidCredentials_ReturnsNull() {
        String username = "testUser";
        String password = "testPassword";
        // No agrega ningún usuario al resultado del mock (credenciales inválidas)

        String result = userService.login(username, password);

        assertNull(result);
        // Verifica que el método del repositorio fue llamado
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void credenciales_ValidCredentials_ReturnsTrue() throws NoSuchAlgorithmException {
        String username = "testUser";
        String password = "testPassword";
        User expectedUser = new User();
        expectedUser.setPassword(password); // Establecer una contraseña válida
        // Agrega el usuario esperado al resultado del mock
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);
        // Simula que las contraseñas coinciden
        when(encriptarService.encriptar(password)).thenReturn(password);

        boolean result = userService.credenciales(username, password);

        assertTrue(result);
        // Verifica que los métodos del repositorio y del servicio de encriptación fueron llamados
        verify(userRepository, times(1)).findByUsername(username);
        verify(encriptarService, times(1)).encriptar(password);
    }

    @Test
    void credenciales_InvalidCredentials_ReturnsFalse() throws NoSuchAlgorithmException {
        String username = "testUser";
        String password = "testPassword";
        // No agrega ningún usuario al resultado del mock (credenciales inválidas)

        boolean result = userService.credenciales(username, password);

        assertFalse(result);
        // Verifica que el método del repositorio fue llamado
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void getUser_ValidUsername_ReturnsUser() {
        String username = "testUser";
        User expectedUser = new User();
        // Agrega el usuario esperado al resultado del mock
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User result = userService.getUser(username);

        assertEquals(expectedUser, result);
        // Verifica que el método del repositorio fue llamado
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void crearUsuario() {
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String password = "password123";
        String email = "john.doe@example.com";
        Rol rol = Rol.CLIENTE;

        userService.crearUsuario(firstName, lastName, username, password, email, rol);

        // Verifica que el método del repositorio fue llamado con los parámetros correctos
        verify(userRepository, times(1)).save(any(User.class));
    }

    // Pruebas InsumoService
    @Test
    void getAllInsumos() {
        ArrayList<Insumo> expectedInsumos = new ArrayList<>();
        // Agrega los insumos esperados al resultado del mock
        when(insumoRepository.findAll()).thenReturn(expectedInsumos);

        List<Insumo> result = insumoService.getAllInsumos();

        assertEquals(expectedInsumos, result);
        // Verifica que el método del repositorio fue llamado
        verify(insumoRepository, times(1)).findAll();
    }

    @Test
    void getInsumoById() {
        Long insumoId = 1L;
        Insumo expectedInsumo = new Insumo();
        // Agrega el insumo esperado al resultado del mock
        when(insumoRepository.findById(insumoId)).thenReturn(Optional.of(expectedInsumo));

        Insumo result = insumoService.getInsumoById(insumoId);

        assertEquals(expectedInsumo, result);
        // Verifica que el método del repositorio fue llamado
        verify(insumoRepository, times(1)).findById(insumoId);
    }

    @Test
    void createInsumo() {
        String nombre = "InsumoTest";
        TipoInsumos tipo = TipoInsumos.CARNES;
        int cantidad = 10;
        double precio = 20.0;
        Date fecha = new Date();

        insumoService.createInsumo(nombre, tipo, cantidad, precio, fecha);

        // Verifica que el método del repositorio fue llamado con los parámetros correctos
        verify(insumoRepository, times(1)).save(any(Insumo.class));
    }
    //TEST COMIDA
    @Test
    void obtenerComidasPorCategoria() {
        Categoria categoria = Categoria.PLATO_PRINCIPAL;
        ArrayList<Comida> expectedComidas = new ArrayList<>();
        // Agrega las comidas esperadas al resultado del mock
        when(comidaRepository.findByCategoriaOrderByNombre(categoria)).thenReturn(expectedComidas);

        ArrayList<Comida> result = comidaService.obtenerComidasPorCategoria(categoria);

        assertEquals(expectedComidas, result);
        // Verifica que el método del repositorio fue llamado
        verify(comidaRepository, times(1)).findByCategoriaOrderByNombre(categoria);
    }

    @Test
    void obtenerMejoresCalificados() {
        ArrayList<Comida> expectedComidas = new ArrayList<>();
        // Agrega las comidas esperadas al resultado del mock
        when(comidaRepository.findTop5ByOrderByCalificacionDesc()).thenReturn(expectedComidas);

        ArrayList<Comida> result = comidaService.obtenerMejoresCalificados();

        assertEquals(expectedComidas, result);
        // Verifica que el método del repositorio fue llamado
        verify(comidaRepository, times(1)).findTop5ByOrderByCalificacionDesc();
    }

    @Test
    void obtenerPromociones() {
        ArrayList<Comida> expectedComidas = new ArrayList<>();
        // Agrega las comidas esperadas al resultado del mock
        when(comidaRepository.findByPromocionIsNotNull()).thenReturn(expectedComidas);

        ArrayList<Comida> result = comidaService.obtenerPromociones();

        assertEquals(expectedComidas, result);
        // Verifica que el método del repositorio fue llamado
        verify(comidaRepository, times(1)).findByPromocionIsNotNull();
    }

    @Test
    public void testCrearPromocion() {
        // Arrange
        String nombre = "Promo1";
        String descripcion = "Descripción de la promoción";
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusDays(7);
        String categoria = "FAST_FOOD";
        TipoDescuento tipoDescuento = TipoDescuento.PORCENTAJE;
        Double descuento = 10.0;
        Promocion mockPromocion = new Promocion(nombre, descripcion, fechaInicio, fechaFin, categoria, tipoDescuento, descuento);
        when(promocionRepository.save(any(Promocion.class))).thenReturn(mockPromocion);

        // Act
        promocionService.crearPromocion(nombre, descripcion, fechaInicio, fechaFin, categoria, tipoDescuento, descuento);

        // Assert
        //verify(promocionRepository, times(1)).save(any(Promocion.class));
        //verify(comidaService, times(1)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testEliminarPromocion() {
        // Arrange
        String nombre = "Promo1";
        Promocion mockPromocion = new Promocion(nombre, "", LocalDateTime.now(), LocalDateTime.now(), "", TipoDescuento.PORCENTAJE, 10.0);
        when(promocionRepository.getById(nombre)).thenReturn(mockPromocion);

        // Act
        //promocionService.eliminarPromocion(nombre);

        // Assert
        //verify(comidaService, times(1)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
        //verify(promocionRepository, times(1)).delete(mockPromocion);
    }

    @Test
    public void testObtenerTodasLasPromociones() {
        // Arrange
        Promocion promo1 = new Promocion("Promo1", "", LocalDateTime.now(), LocalDateTime.now(), "", TipoDescuento.PORCENTAJE, 10.0);
        Promocion promo2 = new Promocion("Promo2", "", LocalDateTime.now(), LocalDateTime.now(), "", TipoDescuento.PORCENTAJE, 15.0);
        List<Promocion> promociones = new ArrayList<>();
        promociones.add(promo1);
        promociones.add(promo2);
        when(promocionRepository.findAll()).thenReturn(promociones);

        // Act
        ArrayList<Promocion> result = promocionService.obtenerTodasLasPromociones();

        // Assert
        assertEquals(2, result.size());
        verify(promocionRepository, times(1)).findAll();
    }

    @Test
    public void testConfigurarPromocionPorPorcentaje() {
        // Arrange
        String categoria = "FAST_FOOD";
        Set<DetalleComidaInsumo> detalleComidaInsumos = new HashSet<>();
        detalleComidaInsumos.add(detalleComidaInsumo);
        double descuento = 10.0;
        TipoDescuento tipoDescuento = TipoDescuento.PORCENTAJE;
        Promocion promo = new Promocion("Promo1", "", LocalDateTime.now(), LocalDateTime.now(), categoria, tipoDescuento, descuento);
        //Comida comida1 = new Comida("Comida1", 20.0, 5.0, detalleComidaInsumos);
        //Comida comida2 = new Comida("Comida2", 30.0, 8, detalleComidaInsumos);
        ArrayList<Comida> comidas = new ArrayList<>();
        //comidas.add(comida1);
        //comidas.add(comida2);
        when(comidaRepository.findByCategoriaOrderByNombre(Categoria.FAST_FOOD)).thenReturn(comidas);

        // Act
        promocionService.configurarPromocion(promo);

        // Assert
        //assertEquals(4.5, comida1.getPrecio(), 0.001);
        //assertEquals(7.2, comida2.getPrecio(), 0.001);
        //verify(comidaService, times(2)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
    }

    @Test
    public void testConfigurarPromocionPorCantidad() {
        // Arrange
        String categoria = "FAST_FOOD";
        Set<DetalleComidaInsumo> detalleComidaInsumos = new HashSet<>();
        detalleComidaInsumos.add(detalleComidaInsumo);
        int cantidadMinima = 5;
        TipoDescuento tipoDescuento = TipoDescuento.CANTIDAD;
        Promocion promo = new Promocion("Promo2", "", LocalDateTime.now(), LocalDateTime.now(), categoria, tipoDescuento, (double) cantidadMinima);
       // Comida comida1 = new Comida("Comida1", 20.0, 5, detalleComidaInsumos);
       // Comida comida2 = new Comida("Comida2", 30.0, 8, detalleComidaInsumos);
        ArrayList<Comida> comidas = new ArrayList<>();
        //comidas.add(comida1);
        //comidas.add(comida2);
        //when(comidaRepository.findByCategoriaOrderByNombre(Categoria.FAST_FOOD)).thenReturn(comidas);

        // Act
        promocionService.configurarPromocion(promo);

        // Assert
        //assertEquals(5.0, comida1.getPrecio(), 0.001);
       // assertEquals(8, comida2.getPrecio(), 0.001);
        //verify(comidaService, times(2)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
    }



}

