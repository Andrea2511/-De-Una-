package co.edu.escuelaing.cvds.project;
import co.edu.escuelaing.cvds.project.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.*;
import co.edu.escuelaing.cvds.project.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ProjectApplicationTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EncriptarService encriptarService;

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
    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private LineaPedidoRepository lineaPedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;
    @InjectMocks
    private LineaPedidoService lineaPedidoService;
    @Mock
    private TarjetaRepository tarjetaRepository;

    @InjectMocks
    private TarjetaService tarjetaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //TEST DE USER
    @Test
    void login_ValidCredentials_ReturnsRole() {
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
    void credenciales_ValidCredentials_ReturnsTrue() {
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
    void credenciales_InvalidCredentials_ReturnsFalse() {
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

    // TEST INSUMO
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

    //TEST PEDIDOS


    //TEST LINEA PEDIDOS
    @Test
    void testCrearLineaPedidoExitoso() {
        // Configurar el comportamiento del mock de comidaRepository
        Long idComida = 1L;
        Comida comidaMock = new Comida();
        comidaMock.setPrecio(10.0);
        when(comidaRepository.findById(idComida)).thenReturn(Optional.of(comidaMock));

        // Configurar el comportamiento del mock de lineaPedidoRepository
        LineaPedido lineaPedidoMock = new LineaPedido();
        when(lineaPedidoRepository.save(any(LineaPedido.class))).thenReturn(lineaPedidoMock);

        // Crear un pedido para asociarlo con la línea de pedido
        Pedido pedido = new Pedido();

        // Ejecutar el método que se está probando
        LineaPedido resultado = lineaPedidoService.crearLineaPedido(pedido, "Coca-Cola", idComida.toString(), new String[]{"Queso", "Tomate"});

        // Verificar que los métodos del repositorio fueron llamados
        verify(comidaRepository, times(1)).findById(idComida);
        verify(lineaPedidoRepository, times(1)).save(any(LineaPedido.class));

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals("Coca-Cola", resultado.getBebida());
        assertEquals(comidaMock, resultado.getComida());
        assertEquals(1, resultado.getCantidad());
        assertEquals(10.0, resultado.getTotal());
        assertEquals("Queso,Tomate", resultado.getIngredientes());
        assertEquals(pedido, resultado.getPedido());
    }

    @Test
    void testCrearLineaPedidoComidaNoEncontrada() {
        // Configurar el comportamiento del mock de comidaRepository para simular que la comida no se encuentra
        Long idComida = 1L;
        when(comidaRepository.findById(idComida)).thenReturn(Optional.empty());

        // No es necesario configurar el comportamiento del mock de lineaPedidoRepository

        // Crear un pedido para asociarlo con la línea de pedido
        Pedido pedido = new Pedido();

        // Ejecutar el método que se está probando
        LineaPedido resultado = lineaPedidoService.crearLineaPedido(pedido, "Coca-Cola", idComida.toString(), new String[]{"Queso", "Tomate"});

        // Verificar que los métodos del repositorio fueron llamados
        verify(comidaRepository, times(1)).findById(idComida);
        verify(lineaPedidoRepository, never()).save(any(LineaPedido.class));

        // Verificar el resultado
        assertNull(resultado);
    }
    //ARREGLAR
    //@Test
    //void pedidoActive_NewUser_CreatesNewPedido() {
        // Arrange
        //User usuario = new User();
        //when(pedidoRepository.findByUserAndEstado(usuario, EstadoPedido.EN_PROCESO)).thenReturn(null);
        //when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
        //Pedido pedidoGuardado = invocation.getArgument(0);
        //pedidoGuardado.setId(1L); // Simulando la asignación de un ID al pedido guardado
        //return pedidoGuardado;
        //});

        // Act
        //Pedido result = pedidoService.pedidoActive(usuario);

        // Assert
        //assertNotNull(result);
        //assertEquals(usuario, result.getUser());
        //assertEquals(EstadoPedido.EN_PROCESO, result.getEstado());
        //verify(pedidoRepository, times(1)).save(any(Pedido.class));
        //verify(userRepository, times(1)).save(usuario);
    //}

    @Test
    void pedidoActive_ExistingPedido_ReturnsExistingPedido() {
        // Arrange
        User usuario = new User();
        Pedido existingPedido = new Pedido();
        existingPedido.setUser(usuario);
        existingPedido.setEstado(EstadoPedido.EN_PROCESO);
        when(pedidoRepository.findByUserAndEstado(usuario, EstadoPedido.EN_PROCESO)).thenReturn(existingPedido);

        // Act
        Pedido result = pedidoService.pedidoActive(usuario);

        // Assert
        assertNotNull(result);
        assertEquals(existingPedido, result);
        verify(pedidoRepository, never()).save(any(Pedido.class));
        verify(userRepository, never()).save(usuario);
    }
    //ARREGLAR
    //@Test
    //void addLineaPedido_NewLineaPedido_ReturnsTrue() {
        // Arrange
        //User usuario = new User();
        //Pedido pedido = new Pedido();
        //pedido.setUser(usuario);
        //pedido.setEstado(EstadoPedido.EN_PROCESO);
        //when(pedidoRepository.findByUserAndEstado(usuario, EstadoPedido.EN_PROCESO)).thenReturn(pedido);
        //when(lineaPedidoService.crearLineaPedido(eq(pedido), anyString(), anyString(), any(String[].class))).thenReturn(new LineaPedido());

        // Act
        //boolean result = pedidoService.addLineaPedido(usuario, "Bebida", "1", new String[]{"Ing1", "Ing2"});

        // Assert
        //assertTrue(result);
        //verify(pedidoRepository, times(1)).save(pedido);
        //verify(lineaPedidoService, times(1)).crearLineaPedido(eq(pedido), anyString(), anyString(), any(String[].class));
    //}
    //ARREGLAR
    //@Test
    //void addLineaPedido_ExistingLineaPedido_ReturnsFalse() {
        // Arrange
        //User usuario = new User();
        //Pedido pedido = new Pedido();
        //pedido.setUser(usuario);
        //pedido.setEstado(EstadoPedido.EN_PROCESO);

        // Inicializa la lista lineasPedido antes de agregar elementos
        //pedido.setLineasPedido(new ArrayList<>());

        //LineaPedido existingLineaPedido = new LineaPedido();
        //existingLineaPedido.setComida(new Comida());
        //pedido.getLineasPedido().add(existingLineaPedido);
        //when(pedidoRepository.findByUserAndEstado(usuario, EstadoPedido.EN_PROCESO)).thenReturn(pedido);

        // Act
        // result = pedidoService.addLineaPedido(usuario, "Bebida", "1", new String[]{"Ing1", "Ing2"});

        // Assert
        //assertFalse(result);
        //verify(pedidoRepository, never()).save(pedido);
        //verify(lineaPedidoService, never()).crearLineaPedido(eq(pedido), anyString(), anyString(), any(String[].class));
    //}


    @Test
    void calcularSubtotal_LineasPedidoExist_CalculatesSubtotal() {
        // Arrange
        //String categoria = "FAST_FOOD";
        Set<DetalleComidaInsumo> detalleComidaInsumos = new HashSet<>();
        detalleComidaInsumos.add(detalleComidaInsumo);
        //double descuento = 10.0;
        //TipoDescuento tipoDescuento = TipoDescuento.PORCENTAJE;
        //Promocion promo = new Promocion("Promo1", "", LocalDateTime.now(), LocalDateTime.now(), categoria, tipoDescuento, descuento);
        //Comida comida1 = new Comida("Comida1", 20.0, 5.0, detalleComidaInsumos);
        //Comida comida2 = new Comida("Comida2", 30.0, 8, detalleComidaInsumos);
        ArrayList<Comida> comidas = new ArrayList<>();
        //comidas.add(comida1);
        //comidas.add(comida2);
        when(comidaRepository.findByCategoriaOrderByNombre(Categoria.FAST_FOOD)).thenReturn(comidas);
        Pedido pedido = new Pedido();
        List<LineaPedido> lineasPedido = new ArrayList<>();
        lineasPedido.add(new LineaPedido(1L, 2, 10.0, "bebida1", "ingredientes1", null, null, null));
        lineasPedido.add(new LineaPedido(2L, 3, 15.0, "bebida2", "ingredientes2", null, null, null));
        pedido.setLineasPedido(lineasPedido);

        // Act
        double subtotal = pedidoService.calcularSubtotal(pedido);

        // Assert
        //assertEquals(4.5, comida1.getPrecio(), 0.001);
        //assertEquals(7.2, comida2.getPrecio(), 0.001);
        //verify(comidaService, times(2)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
        assertEquals(25.0, subtotal);
    }

    @Test
    void calcularCostoTotal_ValidSubtotal_CalculatesCostoTotal() {
        // Arrange
        //String categoria = "FAST_FOOD";
        //Set<DetalleComidaInsumo> detalleComidaInsumos = new HashSet<>();
        //detalleComidaInsumos.add(detalleComidaInsumo);
        //int cantidadMinima = 5;
        //TipoDescuento tipoDescuento = TipoDescuento.CANTIDAD;
        //Promocion promo = new Promocion("Promo2", "", LocalDateTime.now(), LocalDateTime.now(), categoria, tipoDescuento, (double) cantidadMinima);
        //Comida comida1 = new Comida("Comida1", 20.0, 5, detalleComidaInsumos);
        //Comida comida2 = new Comida("Comida2", 30.0, 8, detalleComidaInsumos);
        ArrayList<Comida> comidas = new ArrayList<>();
        //comidas.add(comida1);
        //comidas.add(comida2);
        when(comidaRepository.findByCategoriaOrderByNombre(Categoria.FAST_FOOD)).thenReturn(comidas);
        double subtotal = 25.0;

        // Act
        double costoTotal = pedidoService.calcularCostoTotal(subtotal);

        // Assert
        //assertEquals(5.0, comida1.getPrecio(), 0.001);
        //assertEquals(8, comida2.getPrecio(), 0.001);
        //verify(comidaService, times(2)).actualizarComida(anyLong(), anyString(), anyDouble(), anyInt());
        assertEquals(29.75, costoTotal);
    }

    @Test
    void obtenerLineasPedido_PedidoEnProceso_ReturnsLineasPedido() {
        // Arrange
        User usuario = new User();
        Pedido pedidoEnProceso = new Pedido();
        List<LineaPedido> lineasPedido = new ArrayList<>();
        lineasPedido.add(new LineaPedido());
        pedidoEnProceso.setLineasPedido(lineasPedido);
        when(pedidoRepository.findByUserAndEstado(usuario, EstadoPedido.EN_PROCESO)).thenReturn(pedidoEnProceso);

        // Act
        List<LineaPedido> result = pedidoService.obtenerLineasPedido(usuario);

        // Assert
        assertNotNull(result);
        assertEquals(lineasPedido, result);
    }

    @Test
    void obtenerLineasPedido_PedidoNoEnProceso_ReturnsEmptyList() {
        // Arrange
        User usuario = new User();
        when(pedidoRepository.findByUserAndEstado(usuario, EstadoPedido.EN_PROCESO)).thenReturn(null);

        // Act
        List<LineaPedido> result = pedidoService.obtenerLineasPedido(usuario);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    //TEST TARJETA
    @Test
    void crearTarjeta_SaveTarjetaInRepository() {
        // Arrange
        String password = "password";

        // Act
        tarjetaService.crearTarjeta(password);

        // Assert
        verify(tarjetaRepository, times(1)).save(any(Tarjeta.class));
    }

    @Test
    void obtenerTodasLasTarjetas_TarjetasExist_ReturnsListOfTarjetas() {
        // Arrange
        List<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(new Tarjeta("password1"));
        tarjetas.add(new Tarjeta("password2"));

        when(tarjetaRepository.findAll()).thenReturn(tarjetas);

        // Act
        ArrayList<Tarjeta> result = tarjetaService.obtenerTodasLasTarjetas();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }


    @Test
    void testEquals() {
        // Arrange
        Promocion promocion1 = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        Promocion promocion2 = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        // Act + Assert
        assertEquals(promocion1, promocion2);
    }

    @Test
    void testNotEquals() {
        // Arrange
        Promocion promocion1 = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        Promocion promocion2 = new Promocion("Promo2", "Otra descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "OtraCategoria", TipoDescuento.PORCENTAJE, 5.0);

        // Act + Assert
        assertNotEquals(promocion1, promocion2);
    }

    @Test
    void testHashCode() {
        // Arrange
        Promocion promocion1 = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        Promocion promocion2 = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        // Act + Assert
        assertEquals(promocion1.hashCode(), promocion2.hashCode());
    }

    @Test
    void testNotEqualsHashCode() {
        // Arrange
        Promocion promocion1 = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        Promocion promocion2 = new Promocion("Promo2", "Otra descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "OtraCategoria", TipoDescuento.MONTO_FIJO, 5.0);

        // Act + Assert
        assertNotEquals(promocion1.hashCode(), promocion2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Promocion promocion = new Promocion("Promo1", "Descripción", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Categoria", TipoDescuento.PORCENTAJE, 10.0);

        // Act
        String result = promocion.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Promo1"));
        assertTrue(result.contains("Descripción"));
        assertTrue(result.contains("Categoria"));
        assertTrue(result.contains("10.0"));
    }




}

