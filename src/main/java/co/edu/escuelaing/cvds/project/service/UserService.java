package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.Cliente;
import co.edu.escuelaing.cvds.project.model.Rol;
import co.edu.escuelaing.cvds.project.model.User;
import co.edu.escuelaing.cvds.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncriptarService encriptarService;

    public String login(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            String role = user.getRoles().get(0).toString();
            return role; // Retorna el nombre del rol
        }

        return null; // En caso de credenciales incorrectas
    }

    public boolean credenciales(String username, String password) throws NoSuchAlgorithmException {
        User user = userRepository.findByUsername(username);
        System.out.println("user:"+user);
        String pw = encriptarService.encriptar(password);
        boolean exist = false;
        if(user != null){
            if (user.getPassword().equals(pw)){
                exist = true;
            }
        }
        return exist; // Las credenciales son válidas
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void crearUsuario(String firstName, String lastName, String username, String password, String email, List<Rol> roles){
        User user = new Cliente();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoles(roles);

        userRepository.save(user);
    }

    public boolean verificarEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getEmail() != null && user.getEmail().equals(email);
    }
}
