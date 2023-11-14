package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.Cliente;
import co.edu.escuelaing.cvds.project.model.User;
import co.edu.escuelaing.cvds.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            String role = user.getRol();
            return role; // Retorna el nombre del rol
        }

        return null; // En caso de credenciales incorrectas
    }

    public boolean credenciales(String username, String password) {
        User user = userRepository.findByUsername(username);
        boolean exist = false;
        if(user != null){
            if (user.getPassword().equals(password)){
                exist = true;
            }
        }
        return exist; // Las credenciales son válidas
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }


    public void crearUsuario(String firstName, String lastName, String email, String username, String password){
        User user = new Cliente(firstName, lastName, email, username, password);
        userRepository.save(user);

    }

    public boolean verificarEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getEmail() != null && user.getEmail().equals(email);
    }

}
