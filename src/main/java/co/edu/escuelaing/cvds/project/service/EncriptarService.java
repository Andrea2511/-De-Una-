package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.EncriptacionContrasena;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;

@Service
public class EncriptarService {
    public String encriptar(String password) {
        EncriptacionContrasena encriptacionContrasena = new EncriptacionContrasena();
        encriptacionContrasena.setHashPassword(password);
        return encriptacionContrasena.getPassword();
    }
}
