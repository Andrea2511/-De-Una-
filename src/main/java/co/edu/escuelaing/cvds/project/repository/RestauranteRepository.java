package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Restaurante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CrudRepository<Restaurante, Long> {

}