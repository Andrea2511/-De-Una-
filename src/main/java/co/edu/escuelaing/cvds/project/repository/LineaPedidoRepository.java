package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.model.LineaPedido;
import co.edu.escuelaing.cvds.project.model.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaPedidoRepository extends CrudRepository<LineaPedido, Long> {

    LineaPedido findByPedidoAndComida(Pedido pedido, Comida comida);

}