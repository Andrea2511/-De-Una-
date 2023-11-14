package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Supervisor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends CrudRepository<Supervisor, Long> {

}