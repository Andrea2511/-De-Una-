package co.edu.escuelaing.cvds.project.service;
import co.edu.escuelaing.cvds.project.model.Rol;
import co.edu.escuelaing.cvds.project.model.Session;
import co.edu.escuelaing.cvds.project.model.Tarjeta;
import co.edu.escuelaing.cvds.project.model.User;
import co.edu.escuelaing.cvds.project.repository.SessionRepository;
import co.edu.escuelaing.cvds.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final EncriptarService encriptarService;
     private final TarjetaService tarjetaService;

  
   

    @Autowired
    private SessionRepository sessionRepository;
    public UserService(UserRepository userRepository, EncriptarService encriptarService, SessionRepository sessionRepository,TarjetaService tarjetaService) {
        this.userRepository = userRepository;
        this.encriptarService = encriptarService;
        this.sessionRepository = sessionRepository;
        this.tarjetaService=tarjetaService;
    }

    public String login(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user.getRol().toString(); // Retorna el nombre del rol
        }

        return null; // En caso de credenciales incorrectas
    }

    public boolean credenciales(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println("user:" + user);
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

    public void crearUsuario(String firstName, String lastName, String username, String password, String email, Rol rol){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRol(rol);

        userRepository.save(user);
        adicionarTarjeta(user);
    }
    public void actualizarDatos(User user,String email, String address, String phone, String firstname, String lastname){
        user.setEmail(email != null ? email : user.getEmail());
        user.setDireccion(address != null ? address : user.getDireccion());
        user.setTelefono(phone != null ? phone : user.getTelefono());
        user.setFirstName(firstname != null ? firstname : user.getFirstName());
        user.setLastName(lastname != null ? lastname : user.getLastName());
        userRepository.save(user);
    }

    public void adicionarTarjeta(User user) {

        Tarjeta tarjeta = tarjetaService.crearTarjeta(user);
        user.setTarjeta(tarjeta);
        userRepository.save(user);
    }

    public boolean verificarEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getEmail() != null && user.getEmail().equals(email);
    }

    public Session getSession(String authToken) {

        return sessionRepository.findByToken(UUID.fromString(authToken));
    }
}