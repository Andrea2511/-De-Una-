package co.edu.escuelaing.cvds.project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectApplicationTest {
    /**@InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsultaExitosaConEmpleadoRegistrado() {
        // escenario de prueba con un empleado registrado
        Employee empleadoRegistrado = new Employee(1L, "Nombre", "Apellido", SexoBiologico.MASCULINO, "Rol", "Salario", "IdEmpresa");
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(empleadoRegistrado));

        // Ejecutar la prueba
        List<Employee> result = employeeService.getAll();

        // Verificar que se obtenga una lista no vacía
        assertNotNull(result);

        // Verificar que la lista contenga al menos un empleado
        assertTrue(result.size() > 0);

        // Verificar que el empleado tenga el ID esperado
        assertEquals(1L, result.get(0).getEmployeeId());
    }

    @Test
    public void testConsultaSinResultadosCuandoNoHayEmpleadosRegistrados() {
        // escenario de prueba sin empleados registrados
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        // Ejecutar la prueba
        List<Employee> result = employeeService.getAll();

        // Verificar que la lista esté vacía
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreacionExitosa() {
        // Configurar un escenario de prueba
        Employee empleadoACrear = new Employee(1L, "Nombre", "Apellido", SexoBiologico.MASCULINO, "Rol", "Salario", "IdEmpresa");

        // Ejecutar la prueba
        employeeService.createEmployee(empleadoACrear.getEmployeeId(), empleadoACrear.getFirstName(), empleadoACrear.getLastName(), empleadoACrear.getSexoBiologico(), empleadoACrear.getRole(), empleadoACrear.getSalary(), empleadoACrear.getIdEmpresa());

        // Verificar que el método save del repositorio se haya llamado con el empleado a crear
        verify(employeeRepository, times(1)).save(empleadoACrear);
    }
**/

}
