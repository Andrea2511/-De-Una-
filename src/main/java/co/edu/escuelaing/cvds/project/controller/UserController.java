package co.edu.escuelaing.cvds.project.controller;
import co.edu.escuelaing.cvds.project.service.EncriptarService;
import co.edu.escuelaing.cvds.project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
//import javax.servlet.http.HttpSession; importante

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private EncriptarService encriptarService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login"); // Esta es la página de inicio de sesión
    }

    @PostMapping("/verificar-usuario")
    public void verificarUsuario(@RequestBody Map<String, String> credentials, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        // Obtiene el usuario y la contraseña del cuerpo de la solicitud
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Realiza la verificación del usuario (reemplaza esto con tu lógica de verificación real)
        boolean isUserValid = userService.credenciales(username, password);


        if (isUserValid) {
            String rol = userService.getUser(username).getRol();
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("success", true);
            responseBody.put("message", "Autenticación exitosa");

            switch (rol) {
                case "ADMINISTRADOR" -> responseBody.put("redirect", "/admin"); // Agrega la información de redirección
                case "CLIENTE" -> responseBody.put("redirect", "/cliente");
                case "SUPERVISOR" -> responseBody.put("redirect", "/supervisor");
            }

            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        }  else {
            // Usuario no autenticado, puedes devolver un código de estado diferente si lo deseas
            // En este ejemplo, devolvemos un código de estado 401 (Unauthorized)
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
                    userService.crearUsuario(firstName, lastName, username,encriptarService.encriptar(password) ,email );
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
        return new ModelAndView("admin");
    }
    //falta pagina principal para el administrador
    //modificar para principal para el cliente
    @RequestMapping("/cliente")
    public ModelAndView clientepage() {
        return new ModelAndView("pagecliente"); // Página de empleado
    }

    //modificar pagina principal para el supervisor
    @RequestMapping("/supervisor")
    public ModelAndView supervisorPage() {
        return new ModelAndView("supervisor"); // Página de empleado
    }
}
