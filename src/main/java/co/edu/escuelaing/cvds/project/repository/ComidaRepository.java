package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Categoria;
import co.edu.escuelaing.cvds.project.model.Comida;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ComidaRepository extends CrudRepository<Comida, Long> {

    ArrayList<Comida> findByCategoriaOrderByNombre(Categoria categoria);
    ArrayList<Comida> findByPromocionIsNotNull();
    ArrayList<Comida> findTop5ByOrderByCalificacionDesc();
}
