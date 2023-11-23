package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import co.edu.escuelaing.cvds.project.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private ComidaService comidaService;

    @Autowired
    InsumoService insumoService;

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
    public String inventario(Model model) {
        ArrayList<Insumo> insumos = insumoService.obtenerTodosLosInsumos();
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/carnes")
    public String mostrarCarnes(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.CARNES);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/lacteos")
    public String mostrarLacteos(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.LACTEOS);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/frutas")
    public String mostrarFrutas(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.FRUTAS);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/verduras")
    public String mostrarVerduras(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.VERDURAS);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/cereales")
    public String mostrarCereales(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.CEREALES);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/aceites")
    public String mostrarAceites(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.ACEITES);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/legumbres")
    public String mostrarLegumbres(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.LEGUMBRES);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/inventario/frutosSecos")
    public String mostrarFrutos(Model model) {
        List<Insumo> insumos = insumoService.obtenerComidasPorTipo(TipoInsumos.FRUTOSECO);
        model.addAttribute("insumos", insumos);
        return "admin";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        ArrayList<Comida> comidas = comidaService.obtenerTodasLasComidas();
        model.addAttribute("comidas", comidas);
        return "admin";
    }

    @GetMapping("/guardarPromociones")
    public String addPromos() {
        return "admin";
    }

    @PostMapping("/guardarInsumo")
    public String guardarInsumo(@ModelAttribute Insumo insumo){

        System.out.println("Fecha " + insumo.getFechaVencimiento());
        insumoService.createInsumo(insumo.getNombre(), insumo.getTipo(), insumo.getCantidad(), insumo.getPrecio(), insumo.getFechaVencimiento());

        return "redirect:/admin/inventario";
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


    @PostMapping("/actualizarComida")
    public String actualizarComida(@RequestBody Comida datosEditados) {
        // Buscar la comida por ID en la base de datos
        System.out.println("Recibiendo solicitud para actualizar comida: " + datosEditados);
        comidaService.actualizarComida(datosEditados.getComidaId(), datosEditados.getNombre(), datosEditados.getPrecio(), datosEditados.getCantidad());
        return "redirect:/admin/menu";
    }

}
