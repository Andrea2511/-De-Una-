package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    @Autowired
    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public List<Insumo> getAllInsumos() {
        return (List<Insumo>) insumoRepository.findAll();
    }

    public Insumo getInsumoById(Long insumoId) {
        return insumoRepository.findById(insumoId).orElse(null);
    }

    public void createInsumo(Insumo insumo) {
        insumoRepository.save(insumo);
    }

    public void updateInsumo(Insumo insumo) {
        insumoRepository.save(insumo);
    }

    public void deleteInsumo(Long insumoId) {
        insumoRepository.deleteById(insumoId);
    }

}
