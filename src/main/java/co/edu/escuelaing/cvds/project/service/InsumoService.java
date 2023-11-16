package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void createInsumo(String nombre,String tipo, String cantidad, String precio) {
        Insumo insumo = new Insumo(nombre, tipo, cantidad, precio, new Date(19991129));

        insumoRepository.save(insumo);
    }

    public void updateInsumo(Long insumoId, String nombre,String tipo, String cantidad, String precio) {
        Insumo insumo = insumoRepository.getReferenceById(insumoId);
        insumo.setNombre(!nombre.isEmpty() ? nombre : insumo.getNombre());
        insumo.setTipo(!tipo.isEmpty() ? tipo : insumo.getTipo());
        insumo.setCantidad(!cantidad.isEmpty() ? cantidad : insumo.getCantidad());
        insumo.setPrecio(!precio.isEmpty() ? precio : insumo.getPrecio());

        insumoRepository.save(insumo);
    }

    public void deleteInsumo(Long insumoId) {
        insumoRepository.deleteById(insumoId);
    }

    // Puedes agregar métodos adicionales según tus necesidades.
}
