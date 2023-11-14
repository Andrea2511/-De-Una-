package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.DetalleComidaInsumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleComidaInsumoRepository extends CrudRepository<DetalleComidaInsumo, Long> {

}