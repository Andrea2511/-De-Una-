package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.PreferenciasCliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciasClienteRepository extends CrudRepository<PreferenciasCliente, Long> {

}