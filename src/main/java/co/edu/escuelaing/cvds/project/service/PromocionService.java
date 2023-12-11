package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PromocionService {

    final
    PromocionRepository promocionRepository;
    final
    ComidaRepository comidaRepository;
    final
    ComidaService comidaService;
    @Autowired
    public PromocionService(PromocionRepository promocionRepository, ComidaRepository comidaRepository, ComidaService comidaService) {
        this.promocionRepository = promocionRepository;
        this.comidaRepository = comidaRepository;
        this.comidaService = comidaService;
    }

    public void crearPromocion(String nombre, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, String categoria, TipoDescuento tipoDescuento, Double descuento){
        Promocion promocion = new Promocion();
        promocion.setNombre(nombre);
        promocion.setDescripcion(descripcion);
        promocion.setFechaInicio(fechaInicio);
        promocion.setFechaFin(fechaFin);
        promocion.setCategoria(categoria);
        promocion.setTipoDescuento(tipoDescuento);
        promocion.setDescuento(descuento);


        promocionRepository.save(promocion);
        configurarPromocion(promocion);

    }

    public void eliminarPromocion(String nombre){
        Promocion promocion = promocionRepository.getById(nombre);
        borrarPromocion(promocion);
        promocionRepository.delete(promocion);
    }

    public void configurarPromocion(Promocion promocion) {
        TipoDescuento tipoDescuento = promocion.getTipoDescuento();
        String categoriaString = promocion.getCategoria();
        Categoria categoria = Categoria.valueOf(categoriaString);
        ArrayList<Comida> comidas = comidaRepository.findByCategoriaOrderByNombre(categoria);
        if (tipoDescuento != TipoDescuento.CANTIDAD && tipoDescuento != TipoDescuento.COMPRA_MINIMA){
            for (Comida c : comidas) {
                double precio = c.getPrecio();
                double descuento = precio * (promocion.getDescuento() / 100);
                c.setPrecio(precio - descuento);
                comidaService.actualizarComida(c.getComidaId(),c.getNombre(),c.getPrecio(),c.getCantidad());
            }
        }
    }

    public void borrarPromocion(Promocion promocion){
        TipoDescuento tipoDescuento = promocion.getTipoDescuento();
        String categoriaString = promocion.getCategoria();

        // Verificar si la cadena es nula antes de usarla en valueOf
        if (categoriaString != null) {
            Categoria categoria = Categoria.valueOf(categoriaString);
            ArrayList<Comida> comidas = comidaRepository.findByCategoriaOrderByNombre(categoria);
            if (tipoDescuento != TipoDescuento.CANTIDAD && tipoDescuento != TipoDescuento.COMPRA_MINIMA){
                for (Comida c : comidas) {
                    double precio = c.getPrecio();
                    double descuento = precio * (promocion.getDescuento() / 100);
                    c.setPrecio(precio + descuento);
                    comidaService.actualizarComida(c.getComidaId(),c.getNombre(),c.getPrecio(),c.getCantidad());
                }
            }
        }
    }

    public ArrayList<Promocion> obtenerTodasLasPromociones() {
        return (ArrayList<Promocion>) promocionRepository.findAll();
    }
}

