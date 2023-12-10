package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.model.TipoInsumos;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    @Autowired
    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public List<Insumo> getAllInsumos() {
        return insumoRepository.findAll();
    }

    public Insumo getInsumoById(Long insumoId) {
        return insumoRepository.findById(insumoId).orElse(null);
    }

    public void createInsumo(String nombre, TipoInsumos tipo, int cantidad, double precio, Date fecha) {
        Insumo insumo = new Insumo();
        insumo.setNombre(nombre);
        insumo.setTipo(tipo);
        insumo.setCantidad(cantidad);
        insumo.setPrecio(precio);
        insumo.setFechaVencimiento(fecha);
        insumoRepository.save(insumo);
    }


    public void deleteInsumo(Long insumoId) {
        insumoRepository.deleteById(insumoId);
    }

    public ArrayList<Insumo> obtenerTodosLosInsumos() {return (ArrayList<Insumo>) insumoRepository.findAll();}

    public List<Insumo> obtenerComidasPorTipo(TipoInsumos tipoInsumos) {return insumoRepository.findByTipoOrderByNombre(tipoInsumos);}

    public void actualizarInsumo(Long insumoId, String nombre, double precio, int cantidad, Date fechaVencimiento) {

        Insumo insumoExistente = insumoRepository.findById(insumoId)
                .orElseThrow(() -> new IllegalArgumentException("Comida no encontrada con ID: " + insumoId));

        // Actualizar los datos con los valores editados
        insumoExistente.setNombre(nombre);
        insumoExistente.setPrecio(precio);
        insumoExistente.setCantidad(cantidad);
        insumoExistente.setFechaVencimiento(fechaVencimiento);

        // Guardar la comida actualizada en la base de datos
        insumoRepository.save(insumoExistente);

    }

    public void eliminarInsumo(Long insumoId) {
        Insumo insumoExistente = insumoRepository.findById(insumoId)
                .orElseThrow(() -> new IllegalArgumentException("Comida no encontrada con ID: " + insumoId));

        // Elimina
        insumoRepository.delete(insumoExistente);
    }
}
