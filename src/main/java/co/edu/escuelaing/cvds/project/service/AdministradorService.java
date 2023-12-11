package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.EstadoPedido;
import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.model.Pedido;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdministradorService {

    private final ComidaRepository comidaRepository;
    private final PedidoRepository pedidoRepository;
    private final InsumoRepository insumoRepository;
    @Autowired
    public AdministradorService(ComidaRepository comidaRepository, PedidoRepository pedidoRepository, InsumoRepository insumoRepository) {
        this.comidaRepository = comidaRepository;
        this.pedidoRepository = pedidoRepository;
        this.insumoRepository = insumoRepository;
    }

    public String calcularVentasTotales(){
        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) pedidoRepository.findAll();
        double ventasTotales = 0;
        String ventasT;
        for(Pedido p: pedidos){
            if(p.getEstado() == EstadoPedido.FINALIZADO){
                ventasTotales += p.getCostoTotal();
            }
        }
        ventasT = String.valueOf(ventasTotales);
        return ventasT;
    }

    public String calcularGastosTotales(){
        ArrayList<Insumo> insumos = (ArrayList<Insumo>) insumoRepository.findAll();
        double gastosTotales = 0;
        String gastosT;
        for(Insumo i: insumos){
           gastosTotales += i.getPrecio();
        }
        gastosT = String.valueOf(gastosTotales);
        return gastosT;
    }

    public String calcularIngresosTotales(){
        double vetasTotales = Double.parseDouble(calcularVentasTotales());
        double gastosTotales = Double.parseDouble(calcularGastosTotales());
        double ingresosTotales = vetasTotales - gastosTotales;
        return String.valueOf(ingresosTotales);
    }
}
