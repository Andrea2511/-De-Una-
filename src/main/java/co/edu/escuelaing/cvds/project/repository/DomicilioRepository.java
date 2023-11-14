package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Domicilio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends CrudRepository<Domicilio, Long> {

}