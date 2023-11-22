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
import java.util.Base64;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private InsumoRepository insumoRepository;


    @GetMapping("/dashboard")
    public String mostrarFormulario() {
        return "admin";
    }

    @GetMapping("/pqrs")
    public String pqrs() {
        return "admin";
    }

    @GetMapping("/guardarProducto")
    public String addProduct() {
        return "admin";
    }

    @GetMapping("/guardarInsumo")
    public String addInsumo() {
        return "admin";
    }

    @GetMapping("/inventario")
    public String inventario() {
        return "admin";
    }

    @GetMapping("/menu")
    public String menu() {
        return "admin";
    }

    @GetMapping("/guardarPromociones")
    public String addPromos() {
        return "admin";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@RequestParam("nombre") String nombre, @RequestParam("precio") double precio, @RequestParam("cantidad") int cantidad, @RequestParam("tipoComida") Categoria tipoComida, @RequestParam("imagen") MultipartFile imagen) throws IOException {

        Comida comida = comidaService.crearComida(nombre, 0.0, precio, cantidad, tipoComida);

        if (imagen != null && !imagen.isEmpty()) {
            // Convertir la imagen a base64
            byte[] imageData = imagen.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
    
            // Guardar la representación base64 en la entidad comida
            comida.setRuta(base64Image);
        }
        comidaService.guardarComida(comida);

        return "redirect:/admin/menu";
    }

}
