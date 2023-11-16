package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.Categoria;
import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.model.DetalleComidaInsumo;
import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private InsumoRepository insumoRepository;

    @Value("${upload.path}") // Necesitas configurar esta propiedad en tu archivo application.properties
    private String uploadPath;

    @GetMapping("/guardarProducto")
    public String mostrarFormulario() {
        // Lógica para mostrar el formulario, si es necesario
        return "admin";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@RequestParam("nombre") String nombre, @RequestParam("precio") double precio, @RequestParam("cantidad") int cantidad, @RequestParam("tipoComida") Categoria tipoComida, @RequestParam("imagen") MultipartFile imagen) throws IOException {


        Comida comida = comidaService.crearComida(nombre, 0.0, precio, cantidad, tipoComida);

        if (imagen != null && !imagen.isEmpty()) {
            // Generamos un nombre único para la imagen usando UUID
            String fileName = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

            // Guardamos la imagen en el directorio configurado
            imagen.transferTo(new File(uploadPath + "/" + fileName));

            // Seteamos la ruta de la imagen en la entidad producto
            comida.setRuta(fileName);
        }

        comidaService.guardarComida(comida);

        return "redirect:/admin/guardarProducto";
    }

}
