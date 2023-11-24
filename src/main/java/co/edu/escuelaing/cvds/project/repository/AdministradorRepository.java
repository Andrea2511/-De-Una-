package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long> {

}
