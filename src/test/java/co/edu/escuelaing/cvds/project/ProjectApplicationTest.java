package co.edu.escuelaing.cvds.project;

import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.repository.UserRepository;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import co.edu.escuelaing.cvds.project.service.EncriptarService;
import co.edu.escuelaing.cvds.project.service.InsumoService;
import co.edu.escuelaing.cvds.project.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ProjectApplicationTest {

    @Mock
    private ComidaRepository comidaRepository;

    @InjectMocks
    private ComidaService comidaService;

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
        User mockUser = new Cliente();
        mockUser.setUsername(username);
        mockUser.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Act
        String result = userService.login(username, password);

        // Assert
        assertEquals(mockUser.getRol(), result);
    }

    @Test
    public void testLoginValidCredentials() {
        // Arrange
        User mockUser = new Cliente("John", "Doe", "johndoe", "password123", "john.doe@example.com");
        when(userRepository.findByUsername("johndoe")).thenReturn(mockUser);

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
        User mockUser = new Cliente("Jane", "Doe", "janedoe", "password456", "jane.doe@example.com");
        when(userRepository.findByUsername("janedoe")).thenReturn(mockUser);

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
        userService.crearUsuario(firstName, lastName, username, password, email);

        // Assert
        verify(userRepository, times(1)).save(any(Cliente.class)); // Ajusta según la implementación real
    }

    // Pruebas InsumoService
    @Test
    public void testGetAllInsumos() {
        // Simular el repositorio para devolver una lista de insumos
        List<Insumo> insumos = new ArrayList<>();
        insumos.add(new Insumo("Insumo1", TipoInsumos.CARNES, 10, 5.0, new Date()));
        insumos.add(new Insumo("Insumo2", TipoInsumos.FRUTAS, 20, 3.0, new Date()));

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
        verify(comidaRepository, times(1)).delete(mockComida);
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
        assertSame(mockComidas, result);
    }



}

