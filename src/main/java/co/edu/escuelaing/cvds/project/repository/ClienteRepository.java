package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    // Puedes agregar métodos de consulta personalizados aquí si es necesario.
}