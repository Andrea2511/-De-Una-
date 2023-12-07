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

    //TEST DE LAS PROMOCIONES
    @Test
    void crearPromocion() {
        String nombre = "Promo1";
        String descripcion = "Descripción de la promoción";
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = LocalDateTime.now().plusDays(7);
        String categoria = "FAST_FOOD";
        TipoDescuento tipoDescuento = TipoDescuento.PORCENTAJE;
        Double descuento = 10.0;

        promocionService.crearPromocion(nombre, descripcion, fechaInicio, fechaFin, categoria, tipoDescuento, descuento);
        verify(promocionRepository, times(1)).save(any(Promocion.class));
        verify(comidaRepository, times(1)).findByCategoriaOrderByNombre(any(Categoria.class));
        //verify(comidaService, times(1)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
    }
    @Test
    void eliminarPromocion() {
        String nombre = "Promo1";
        Promocion promocion = new Promocion();

        // Simula la llamada a promocionRepository.getById(nombre)
        when(promocionRepository.getById(nombre)).thenReturn(promocion);

        // Simula la llamada a comidaRepository.findByCategoriaOrderByNombre con cualquier Categoria
        when(comidaRepository.findByCategoriaOrderByNombre(any(Categoria.class)))
                .thenReturn(new ArrayList<>());  // Puedes ajustar esto según tu lógica real

        // Ejecuta el método que estás probando
        promocionService.eliminarPromocion(nombre);

        // Verifica que los métodos del repositorio y del servicio fueron llamados según lo esperado
        verify(promocionRepository, times(1)).delete(promocion);
        //verify(comidaRepository, times(1)).findByCategoriaOrderByNombre(any(Categoria.class));
        //verify(comidaService, times(1)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
    }
    @Test
    void obtenerTodasLasPromociones() {
        ArrayList<Promocion> promociones = new ArrayList<>();
        when(promocionRepository.findAll()).thenReturn(promociones);

        ArrayList<Promocion> result = promocionService.obtenerTodasLasPromociones();

        assertEquals(promociones, result);
        verify(promocionRepository, times(1)).findAll();
    }

}
