package co.edu.escuelaing.cvds.project;
import co.edu.escuelaing.cvds.project.model.Rol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (args) -> {

			/** if (!userService.credenciales(username, password)) {
					userService.crearUsuario(firstName, lastName, username,encriptarService.encriptar(password) ,email , Rol.CLIENTE);
					responseBody.put("success", true);
					responseBody.put("message", "Registro exitoso");
					responseBody.put("redirect", "/login");
					return new ResponseEntity<>(responseBody, HttpStatus.OK);
				}**/
		};
	};
}
