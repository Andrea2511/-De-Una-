package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.repository.PagoRepository;
import co.edu.escuelaing.cvds.project.model.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public void procesarPago(Pago pago) {
        // Lógica para procesar el pago, por ejemplo, guardar en la base de datos
        pagoRepository.save(pago);
    }
}