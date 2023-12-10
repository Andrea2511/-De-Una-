package co.edu.escuelaing.cvds.project;
import co.edu.escuelaing.cvds.project.model.Rol;
import co.edu.escuelaing.cvds.project.service.EncriptarService;
import co.edu.escuelaing.cvds.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private EncriptarService encriptarService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			if (!userService.credenciales("admin", "admin")) {
					userService.crearUsuario("admin", "admin", "admin", encriptarService.encriptar("admin") , "deuna@gmail.com", Rol.ADMINISTRADOR);
				}
			if (!userService.credenciales("supervisor", "supervisor")) {
				userService.crearUsuario("supervisor", "supervisor", "supervisor", encriptarService.encriptar("supervisor") , "supervisordeuna@gmail.com", Rol.SUPERVISOR);
			}
		};
	}
}
