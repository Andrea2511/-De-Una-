package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TarjetaService {

    @Autowired
    UserService userService;
    @Autowired
    TarjetaRepository tarjetaRepository;

    public void crearTarjeta(String password){
        Tarjeta tarjeta = new Tarjeta(password);
        tarjetaRepository.save(tarjeta);
    }
    public void setSaldo(String saldo,String authToken){
            Session session = userService.getSession(authToken);
            User user = session.getUser();
            Tarjeta tarjeta = user.getTarjeta();
            tarjeta.setSaldoT(Integer.valueOf(saldo));
    }
    public void redimirPuntos(String authToken){
        Session session = userService.getSession(authToken);
        User user = session.getUser();
        Tarjeta tarjeta = user.getTarjeta();
        tarjeta.redimirPuntos();
    }
    public ArrayList<Tarjeta> obtenerTodasLasTarjetas() {
        return (ArrayList<Tarjeta>) tarjetaRepository.findAll();
    }
}

