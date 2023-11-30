package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import co.edu.escuelaing.cvds.project.repository.InsumoRepository;
import co.edu.escuelaing.cvds.project.repository.PromocionRepository;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import co.edu.escuelaing.cvds.project.service.InsumoService;
import co.edu.escuelaing.cvds.project.service.PromocionService;
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
import java.time.LocalDateTime;
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

    @Autowired
    private PromocionService promocionService;




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

    @GetMapping("/guardarPromociones")
    public String addPromocion(Model model) {;
        ArrayList<Promocion> promociones = promocionService.obtenerTodasLasPromociones();
        model.addAttribute("promociones", promociones);
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

    @PostMapping ("/guardarPromociones")
    public String guardarPromociones(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("fechaInicio")LocalDateTime fechaInicio, @RequestParam("fechaFin")LocalDateTime fechaFin, @RequestParam("categoria") String categoria, @RequestParam("tipoDescuento")TipoDescuento tipoDescuento,@RequestParam("descuento")Double descuento) {
        promocionService.crearPromocion(nombre,descripcion,fechaInicio,fechaFin,categoria,tipoDescuento,descuento);
        return "redirect:/admin/menu";
    }

    @PostMapping("/eliminarPromociones")
    public String eliminarPromocion(@PathVariable String nombre) {
        promocionService.eliminarPromocion(nombre);
        return "redirect:/admin/menu";
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

    @PostMapping("/eliminarComida")
    public String eliminarComida(@PathVariable Long comidaId) {
        System.out.println("comidaId: " + comidaId);
        comidaService.eliminarComida(comidaId);
        return "redirect:/admin/menu";
    }

    @PostMapping("/actualizarInsumo")
    public String actualizarInventario(@RequestBody Insumo datosEditados) {
        // Buscar la comida por ID en la base de datos
        insumoService.actualizarInsumo(datosEditados.getInsumoId(), datosEditados.getNombre(), datosEditados.getPrecio(), datosEditados.getCantidad(), datosEditados.getFechaVencimiento());
        return "redirect:/admin/menu";
    }

    @PostMapping("/eliminarInsumo")
    public String eliminarInsumo(@PathVariable Long insumoId) {
        insumoService.eliminarInsumo(insumoId);
        return "redirect:/admin/menu";
    }

}
