package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.LineaPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaPedidoRepository extends CrudRepository<LineaPedido, Long> {

}