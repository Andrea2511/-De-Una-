package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {

}