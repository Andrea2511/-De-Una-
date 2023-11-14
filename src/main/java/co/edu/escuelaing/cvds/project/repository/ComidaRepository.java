package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Comida;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends CrudRepository<Comida, Long> {

}
