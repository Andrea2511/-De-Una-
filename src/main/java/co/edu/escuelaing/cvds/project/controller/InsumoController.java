package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class InsumoController {

    @Autowired
    InsumoService insumoService;
    @GetMapping("/index")
    public String index() {
        // Lógica para la página de inicio
        return "index"; // Esto devuelve el nombre de la plantilla Thymeleaf correspondiente
    }
    @GetMapping("/inventario")
    public String insumos(Model model) {
        List<Insumo> insumoList = insumoService.getAllInsumos();
        model.addAttribute("insumoList", insumoList);

        return "inventario"; // Nombre de la vista para mostrar la lista de insumos.
    }

    @PostMapping("/inventario/create")
    public String createInsumo(
            @RequestParam String nombre,
            @RequestParam String tipo,
            @RequestParam String cantidad,
            @RequestParam String precio

    ) {
        insumoService.createInsumo(nombre, tipo, cantidad, precio);
        return "redirect:/inventario"; // Redirige a la página de lista de insumos después de la creación.
    }

    @GetMapping("/insumos/edit/{insumoId}")
    public String showEditForm(@PathVariable Long insumoId, Model model) {
        Insumo insumo = insumoService.getInsumoById(insumoId);
        model.addAttribute("insumo", insumo);
        return "edit-insumo"; // Nombre de la vista para editar un insumo existente.
    }

    @PostMapping("/inventario/edit")
    public String updateInsumo(
            @RequestParam Long insumoId,
            @RequestParam String nombre,
            @RequestParam String tipo,
            @RequestParam String cantidad,
            @RequestParam String precio
    ) {
        insumoService.updateInsumo(insumoId, nombre, tipo, cantidad, precio);
        return "redirect:/inventario"; // Redirige a la página de lista de insumos después de la actualización.
    }

    @PostMapping("/inventario/delete")
    public String deleteInsumo(@RequestParam Long insumoId) {
        insumoService.deleteInsumo(insumoId);
        return "redirect:/inventario"; // Redirige a la página de lista de insumos después de la eliminación.
    }
}
