package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.LineaPedidoRepository;
import co.edu.escuelaing.cvds.project.repository.PedidoRepository;
import co.edu.escuelaing.cvds.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LineaPedidoService lineaPedidoService;

    public Pedido pedidoActive(User usuarioEnSesion) {

        Pedido pedido = pedidoRepository.findByUserAndEstado(usuarioEnSesion, EstadoPedido.EN_PROCESO);

        if (pedido == null){
            crearNuevoPedido(usuarioEnSesion);
            pedido = pedidoRepository.findByUserAndEstado(usuarioEnSesion, EstadoPedido.EN_PROCESO);
        }

        return pedido;
    }

    public boolean addLineaPedido(User usuarioEnSesion, String bebida, String idComida, String[] ingredientes) {
        Pedido pedido = pedidoActive(usuarioEnSesion);

        // Verificar si ya existe una línea de pedido para la misma comida
        if (existeLineaPedidoParaComida(pedido, idComida)) {
            // Devolver false para indicar que no se creó la línea de pedido
            return false;
        }

        LineaPedido lineaPedido = lineaPedidoService.crearLineaPedido(pedido, bebida, idComida, ingredientes);

        pedido.getLineasPedido().add(lineaPedido);
        pedido.setSubtotal(calcularSubtotal(pedido));
        System.out.println("Subtotal: " + pedido.getSubtotal());

        pedido.setCostoTotal(calcularCostoTotal(pedido.getSubtotal()));
        pedidoRepository.save(pedido);

        // Devolver true para indicar que se creó la línea de pedido
        return true;
    }

    private boolean existeLineaPedidoParaComida(Pedido pedido, String idComida) {
        if (pedido.getLineasPedido() != null) {
            for (LineaPedido lineaPedido : pedido.getLineasPedido()) {
                if (String.valueOf(lineaPedido.getComida().getComidaId()).equals(idComida)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void crearNuevoPedido(User usuarioEnSesion) {

        Pedido pedido = Pedido.builder()
                .lineasPedido(new ArrayList<>())
                .subtotal(0.0)
                .costoTotal(0.0)
                .fechaInicio(LocalDateTime.now())
                .estado(EstadoPedido.EN_PROCESO)
                .user(usuarioEnSesion)
                .build();

        //agrega el pedido al usuario
        List<Pedido> pedidos = usuarioEnSesion.getPedidos();
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        pedidos.add(pedido);
        usuarioEnSesion.setPedidos(pedidos);


        userRepository.save(usuarioEnSesion);
        pedidoRepository.save(pedido);
    }

    private double calcularSubtotal(Pedido pedido) {

        return pedido.getLineasPedido().stream().mapToDouble(LineaPedido::getTotal).sum();
    }

    private double calcularCostoTotal(double subtotal) {
        return subtotal + (subtotal * 0.19);
    }

}
