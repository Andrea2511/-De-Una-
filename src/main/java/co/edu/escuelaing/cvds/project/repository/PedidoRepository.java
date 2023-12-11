package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.EstadoPedido;
import co.edu.escuelaing.cvds.project.model.Pedido;
import co.edu.escuelaing.cvds.project.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    List<Pedido> findByUser(User user);
    Pedido findByUserAndEstado(User usuario, EstadoPedido estado);

}