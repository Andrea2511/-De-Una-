package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.Comida;
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
        Insumo insumo = new Insumo(nombre, tipo, cantidad, precio, fecha);

        insumoRepository.save(insumo);
    }

    public void updateInsumo(Long insumoId, String nombre, TipoInsumos tipo, int cantidad, double precio) {
        Insumo insumo = insumoRepository.getReferenceById(insumoId);
        insumo.setNombre(!nombre.isEmpty() ? nombre : insumo.getNombre());
        insumo.setTipo(tipo);
        insumo.setCantidad(cantidad);
        insumo.setPrecio(precio);

        insumoRepository.save(insumo);
    }

    public void deleteInsumo(Long insumoId) {
        insumoRepository.deleteById(insumoId);
    }

    public ArrayList<Insumo> obtenerTodosLosInsumos() {return (ArrayList<Insumo>) insumoRepository.findAll();}

    public List<Insumo> obtenerComidasPorTipo(TipoInsumos tipoInsumos) {return insumoRepository.findByTipoOrderByNombre(tipoInsumos);}
}
