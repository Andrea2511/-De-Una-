package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.model.LineaPedido;
import co.edu.escuelaing.cvds.project.model.Pedido;
import co.edu.escuelaing.cvds.project.model.User;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.LineaPedidoRepository;
import co.edu.escuelaing.cvds.project.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineaPedidoService {

    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public LineaPedido crearLineaPedido(Pedido pedido, String bebida, String idComida, String[] ingredientes) {

        LineaPedido lineaPedido = new LineaPedido();

        long idComidaLong = Long.parseLong(idComida);
        Comida comida = obtenerComidaPorId(idComidaLong);

        lineaPedido.setBebida(bebida);
        lineaPedido.setComida(comida);
        lineaPedido.setCantidad(1);
        lineaPedido.setTotal(comida.getPrecio() * lineaPedido.getCantidad());
        lineaPedido.setIngredientes(concatenarIngredientes(ingredientes));

        // Asignar la línea de pedido al pedido en proceso
        lineaPedido.setPedido(pedido);

        // Guardar la línea de pedido
        lineaPedidoRepository.save(lineaPedido);
        return lineaPedido;
    }

    private Comida obtenerComidaPorId(Long idComida) {
        return comidaRepository.findById(idComida).orElse(null);
    }

    private String concatenarIngredientes(String[] ingredientes) {

        return String.join(",", ingredientes);
    }
}
