package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}