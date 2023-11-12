package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Insumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends CrudRepository<Insumo, Long> {

    // Puedes agregar métodos de consulta personalizados aquí si es necesario.
}
