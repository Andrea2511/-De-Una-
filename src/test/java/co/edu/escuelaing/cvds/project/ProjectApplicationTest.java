package co.edu.escuelaing.cvds.project;
import co.edu.escuelaing.cvds.project.model.Categoria;
import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.model.Promocion;
import co.edu.escuelaing.cvds.project.model.TipoDescuento;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.PromocionRepository;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import co.edu.escuelaing.cvds.project.service.PromocionService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.model.DetalleComidaInsumo;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.repository.UserRepository;

import co.edu.escuelaing.cvds.project.service.EncriptarService;
import co.edu.escuelaing.cvds.project.service.InsumoService;
import co.edu.escuelaing.cvds.project.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;



public class ProjectApplicationTest {
    @Mock
    private DetalleComidaInsumo detalleComidaInsumo;

    @Mock
    private PromocionRepository promocionRepository;

    @Mock
    private ComidaRepository comidaRepository;

    @Mock
    private ComidaService comidaService;

    @InjectMocks
    private PromocionService promocionService;


    @Mock
    private EncriptarService encriptarService;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InsumoRepository insumoRepository;

    @InjectMocks
    private InsumoService insumoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Pruebas login en UserService
    @Test
    public void testLogin() {
        // Arrange
        String username = "johndoe";
        String password = "password123";
        //User mockUser = new Cliente();
        //mockUser.setUsername(username);
        //mockUser.setPassword(password);
        //when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Act
        String result = userService.login(username, password);

        // Assert
        //assertEquals(mockUser.getRol(), result);
    }

    @Test
    public void testLoginValidCredentials() {
        // Arrange
        //User mockUser = new Cliente("John", "Doe", "johndoe", "password123", "john.doe@example.com");
        //when(userRepository.findByUsername("johndoe")).thenReturn(mockUser);

        // Act
        String role = userService.login("johndoe", "password123");

        // Assert
        assertEquals("CLIENTE", role);
    }

    @Test
    public void testLoginInvalidCredentials() {
        // Arrange
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(null);

        // Act
        String role = userService.login("nonexistentuser", "invalidpassword");

        // Assert
        assertNull(role);
    }

    @Test
    public void testCredencialesValidCredentials() throws NoSuchAlgorithmException {
        // Arrange
        //User mockUser = new Cliente("Jane", "Doe", "janedoe", "password456", "jane.doe@example.com");
        //when(userRepository.findByUsername("janedoe")).thenReturn(mockUser);

        // Act
        boolean isValid = userService.credenciales("janedoe", "password456");

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testCredencialesInvalidCredentials() throws NoSuchAlgorithmException {
        // Arrange
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(null);

        // Act
        boolean isValid = userService.credenciales("nonexistentuser", "invalidpassword");

        // Assert
        assertFalse(isValid);
    }
    @Test
    public void testEncriptarPassword() throws NoSuchAlgorithmException {
        // Arrange
        String originalPassword = "password123";
        String hashedPassword = "hashedPassword123";
        when(encriptarService.encriptar(originalPassword)).thenReturn(hashedPassword);

        // Act
        String result = encriptarService.encriptar(originalPassword);

        // Assert
        assertEquals(hashedPassword, result);
    }

    @Test
    public void testCrearUsuario() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String password = "password123";
        String email = "john.doe@example.com";

        // Act
        //userService.crearUsuario(firstName, lastName, username, password, email);

        // Assert
        //verify(userRepository, times(1)).save(any(Cliente.class)); // Ajusta según la implementación real
    }

    // Pruebas InsumoService
    @Test
    public void testGetAllInsumos() {
        // Simular el repositorio para devolver una lista de insumos
        List<Insumo> insumos = new ArrayList<>();
        //insumos.add(new Insumo("Insumo1", TipoInsumos.CARNES, 10, 5.0, new Date()));
        //insumos.add(new Insumo("Insumo2", TipoInsumos.FRUTAS, 20, 3.0, new Date()));

        when(insumoRepository.findAll()).thenReturn(insumos);

        // Llamar al método en el servicio y verificar el resultado
        List<Insumo> result = insumoService.getAllInsumos();

        assertEquals(2, result.size());
        assertEquals("Insumo1", result.get(0).getNombre());
        assertEquals("Insumo2", result.get(1).getNombre());

        // Verificar que el método del repositorio fue llamado una vez
        verify(insumoRepository, times(1)).findAll();
    }
    /**

    public void testGetInsumoById() {
        // Simular el repositorio para devolver un insumo con ID específico
        Long insumoId = 1L;
        Insumo insumo = new Insumo("Insumo1", TipoInsumos.CARNES, 10, 5.0, new Date());
        insumo.setInsumoId(insumoId);

        // Asegurarse de que findById devuelva un Optional no vacío
        when(insumoRepository.findById(insumoId)).thenReturn(Optional.of(insumo));

        // Llamar al método en el servicio y verificar el resultado
        Insumo result = insumoService.getInsumoById(insumoId);

        // Verificar que el resultado no es nulo antes de acceder a sus propiedades
        if (result != null) {
            assertEquals("Insumo1", result.getNombre());
        } else {
            fail("El resultado del servicio es nulo");
        }

        // Verificar que el método del repositorio fue llamado una vez
        verify(insumoRepository, times(1)).findById(insumoId);
    }
     **/
    @Test
    public void testActualizarInsumo() {

    }


    // Prueba para el método eliminarComida en ComidaService
    @Test
    public void testEliminarComida() {
        // Arrange
        Long comidaId = 1L;
        Comida mockComida = new Comida();
        when(comidaRepository.findById(comidaId)).thenReturn(java.util.Optional.ofNullable(mockComida));

        // Act
        comidaService.eliminarComida(comidaId);

        // Assert
        //verify(comidaRepository, times(1)).delete(mockComida);
    }
    @Test
    public void testObtenerComidasPorCategoria() {
        // Arrange
        Categoria categoria = Categoria.PLATO_PRINCIPAL;
        ArrayList<Comida> mockComidas = new ArrayList<>();
        when(comidaRepository.findByCategoriaOrderByNombre(categoria)).thenReturn(mockComidas);

        // Act
        List<Comida> result = comidaService.obtenerComidasPorCategoria(categoria);

        // Assert
        //assertSame(mockComidas, result);
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
        //Comida comida1 = new Comida("Comida1", 20.0, 5, detalleComidaInsumos);
        //Comida comida2 = new Comida("Comida2", 30.0, 8, detalleComidaInsumos);
        ArrayList<Comida> comidas = new ArrayList<>();
        //comidas.add(comida1);
        //comidas.add(comida2);
        when(comidaRepository.findByCategoriaOrderByNombre(Categoria.FAST_FOOD)).thenReturn(comidas);

        // Act
        promocionService.configurarPromocion(promo);

        // Assert
        //assertEquals(5.0, comida1.getPrecio(), 0.001);
        //assertEquals(8, comida2.getPrecio(), 0.001);
        //verify(comidaService, times(2)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
    }



}

