package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.Transaccion;
import co.edu.escuelaing.cvds.project.repository.TarjetaRepository;
import co.edu.escuelaing.cvds.project.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionesService {

    @Autowired
    private TransaccionRepository transaccionRepository;
    public Transaccion obtenerTransaccionPorId(Long id) {
        return transaccionRepository.getById(id);
    }
}
