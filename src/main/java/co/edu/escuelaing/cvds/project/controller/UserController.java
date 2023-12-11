package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.Rol;
import co.edu.escuelaing.cvds.project.model.Session;
import co.edu.escuelaing.cvds.project.model.User;
import co.edu.escuelaing.cvds.project.repository.PedidoRepository;
import co.edu.escuelaing.cvds.project.repository.SessionRepository;
import co.edu.escuelaing.cvds.project.service.EncriptarService;
import co.edu.escuelaing.cvds.project.service.TarjetaService;
import co.edu.escuelaing.cvds.project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
//import javax.servlet.http.HttpSession; importante

@Controller
public class UserController {

    final
    UserService userService;

    final
    PedidoRepository pedidoRepository;

    final
    SessionRepository sessionRepository;

    private final EncriptarService encriptarService;
    @Autowired
    public UserController(UserService userService, PedidoRepository pedidoRepository, SessionRepository sessionRepository, EncriptarService encriptarService) {
        this.userService = userService;
        this.pedidoRepository = pedidoRepository;
        this.sessionRepository = sessionRepository;
        this.encriptarService = encriptarService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login"); // Esta es la página de inicio de sesión
    }

    @PostMapping("/verificar-usuario")
    public void verificarUsuario(@RequestBody Map<String, String> credentials, HttpServletResponse response) throws IOException {
        // Obtiene el usuario y la contraseña del cuerpo de la solicitud
        String username = credentials.get("username");
        System.out.println("username:" + username);
        String password = credentials.get("password");
        System.out.println("password:" + password);
        boolean isUserValid = userService.credenciales(username, password);
        System.out.println("password:" + isUserValid);


        if (isUserValid) {
            User user = userService.getUser(username);
            Rol rol = user.getRol();
            System.out.println("rol:" + rol);
            Map<String, Object> responseBody = new HashMap<>();

            Session session = new Session(UUID.randomUUID(), Instant.now(), user);
            sessionRepository.save(session);
            responseBody.put("success", true);
            responseBody.put("message", "Autenticación exitosa");
            responseBody.put("authToken", session.getToken());

            switch (rol) {
                case ADMINISTRADOR -> responseBody.put("redirect", "/admin/dashboard"); // Agrega la información de redirección
                case CLIENTE -> responseBody.put("redirect", "/cliente/dashboard");
                case SUPERVISOR -> responseBody.put("redirect", "/supervisor");
            }

            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales incorrectas");
        }
    }


    @PostMapping("/registrar-usuario")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Map<String, String> credentials) {

        Map<String, Object> responseBody = new HashMap<>();

        try {
            String firstName = credentials.get("firstName");
            String lastName = credentials.get("lastName");
            String username = credentials.get("username");
            String email = credentials.get("email");
            String password = credentials.get("password");

            boolean isUserValid = userService.credenciales(username, password);

            if (!isUserValid) {
                if (!userService.verificarEmail(email)) {
                    userService.crearUsuario(firstName, lastName, username, encriptarService.encriptar(password) , email, Rol.CLIENTE);
                    responseBody.put("success", true);
                    responseBody.put("message", "Registro exitoso");
                    responseBody.put("redirect", "/login");
                    return new ResponseEntity<>(responseBody, HttpStatus.OK);
                } else {
                    responseBody.put("success", false);
                    responseBody.put("error", "Ya existe un usuario con este correo");
                    return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
                }
            } else {
                responseBody.put("success", false);
                responseBody.put("error", "Ya existe este usuario");
                return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();

            responseBody.put("success", false);
            responseBody.put("error", e.getMessage());

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @RequestMapping("/cliente")
    public String redirectToCliente() {

        return "redirect:/cliente/dashboard";
    }

    //modificar pagina principal para el supervisor
    @RequestMapping("/supervisor")
    public ModelAndView supervisorPage() {
        return new ModelAndView("supervisor"); // Página de empleado
    }
}
