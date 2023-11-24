package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Promocion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends CrudRepository<Promocion, Long> {

}