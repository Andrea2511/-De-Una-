package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.DetalleComidaInsumoId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleComidaInsumoIdRepository extends CrudRepository<DetalleComidaInsumoId, Long> {

}