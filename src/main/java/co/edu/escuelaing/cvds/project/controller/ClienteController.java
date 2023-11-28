package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.Categoria;
import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.model.User;
import co.edu.escuelaing.cvds.project.service.ComidaService;
import co.edu.escuelaing.cvds.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String mostrarTodasLasComidas(Model model) {
        ArrayList<Comida> comidas = comidaService.obtenerTodasLasComidas();
        model.addAttribute("comidas", comidas);
        return "pagecliente";
    }

    @GetMapping("/postres")
    public String mostrarPostres(Model model) {
        List<Comida> postres = comidaService.obtenerComidasPorCategoria(Categoria.POSTRE);
        model.addAttribute("comidas", postres);
        return "pagecliente";
    }

    @GetMapping("/platoPrincipal")
    public String mostrarPlatoPrincipal(Model model) {
        List<Comida> platoPrincipal = comidaService.obtenerComidasPorCategoria(Categoria.PLATO_PRINCIPAL);
        model.addAttribute("comidas", platoPrincipal);
        return "pagecliente";
    }

    @GetMapping("/promociones")
    public String mostrarPromociones(Model model) {
        List<Comida> promociones = comidaService.obtenerComidasConPromocion();
        model.addAttribute("comidas", promociones);
        return "pagecliente";
    }

    @GetMapping("/mejoresCalificados")
    public String mostrarMejoresCalificados(Model model) {
        List<Comida> mejoresCalificados = comidaService.obtenerMejoresCalificados();
        model.addAttribute("comidas", mejoresCalificados);
        return "pagecliente";
    }

    @GetMapping("/fastFood")
    public String mostrarFastFood(Model model) {
        List<Comida> fastFood = comidaService.obtenerComidasPorCategoria(Categoria.FAST_FOOD);
        model.addAttribute("comidas", fastFood);
        return "pagecliente";
    }


}
