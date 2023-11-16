package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.EncriptacionContraseña;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class EncriptarService {


    public String encriptar(String password) throws NoSuchAlgorithmException {
        EncriptacionContraseña encriptacionContraseña = new EncriptacionContraseña();
        encriptacionContraseña.setHashPassword(password);
        String PW = encriptacionContraseña.getPassword();
        return PW;
    }
}
