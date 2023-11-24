package co.edu.escuelaing.cvds.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.escuelaing.cvds.project.model.Pago;
import co.edu.escuelaing.cvds.project.service.PagoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// PagoController.java
@Controller
@RequestMapping("/pago")
public class PagoController {
    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/formularioPago")
    public ModelAndView mostrarFormularioPago() {
        return new ModelAndView("formularioPago", "pago", new Pago());
    }

    @PostMapping("/realizarPago")
    public String procesarPago(@ModelAttribute Pago pago) {
        // Aquí puedes agregar lógica adicional antes de procesar el pago, si es necesario
        pagoService.procesarPago(pago);

        // Redirige a una página de confirmación de pago (crea la página confirmacionPago.html)
        return "redirect:/pago/confirmacionPago";
    }

    @GetMapping("/confirmacionPago")
    public String mostrarConfirmacionPago() {
        return "confirmacionPago";
    }
}

