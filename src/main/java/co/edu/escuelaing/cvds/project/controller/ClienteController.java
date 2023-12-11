package co.edu.escuelaing.cvds.project.controller;

import co.edu.escuelaing.cvds.project.model.*;
import co.edu.escuelaing.cvds.project.repository.SessionRepository;
import co.edu.escuelaing.cvds.project.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ComidaService comidaService;

    @Autowired
    private UserService userService;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private TransaccionesService transaccionesService;

    @Autowired
    private PedidoService pedidoService;

    @ModelAttribute("usuario")  // Agregar un atributo global al modelo
    public User usuario(HttpServletRequest request) {
        return obtenerUsuarioEnSesion(request);
    }

    @ModelAttribute("tarjeta")  // Agregar un atributo global al modelo
    public Tarjeta target(HttpServletRequest request) {
        return obtenerUsuarioEnSesion(request).getTarjeta();
    }

    @ModelAttribute("transacciones")  // Agregar un atributo global al modelo
    public List<Transaccion> transacciones(HttpServletRequest request) {
        return obtenerUsuarioEnSesion(request).getTarjeta().getTransacciones();
    }

    @ModelAttribute("lineasPedido")
    public List<LineaPedido> addGlobalLineasPedidos(HttpServletRequest request, Model model) {
        List<LineaPedido> lineasPedidos = obtenerLineasPedidos(request);
        model.addAttribute("lineasPedidos", lineasPedidos);
        return lineasPedidos;
    }

    @ModelAttribute("pedido")
    public Pedido addGlobalPedido(HttpServletRequest request, Model model) {
        Pedido pedido = obtenerPedido(request);
        model.addAttribute("pedido", pedido);
        return pedido;
    }

    @GetMapping("/dashboard")
    public String mostrarTodasLasComidas(Model model) {
        ArrayList<Comida> comidas = comidaService.obtenerTodasLasComidas();
        model.addAttribute("comidas", comidas);
        return "pagecliente";
    }

    @GetMapping("/instantCard")
    public String instantCard(Model model, HttpServletRequest request) {

        List<Transaccion> transacciones = tarjetaService.obtenerTransacciones(obtenerUsuarioEnSesion(request).getTarjeta());
        model.addAttribute("transacciones", transacciones);
        return "pagecliente";
    }

    @GetMapping("/pagos")
    public String pagos() {
        return "pagos";
    }

    @GetMapping("/historial")
    public String historial() {
        return "pagecliente";
    }

    @GetMapping("/cuenta")
    public String cuenta() {
        return "pagecliente";
    }

    @GetMapping("/detallePago")
    public String detallePago() {
        return "detallePago";
    }

    @GetMapping("/pqrs")
    public String pqrs() {
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

    @PostMapping("/carritoCompras")
    public ResponseEntity<Map<String, String>> carritoCompras(
            @RequestParam("idComida") String idComida,
            @RequestParam(value = "ingredientes", required = false) String[] ingredientes,
            @RequestParam(value = "bebidas", required = false) String bebida,
            HttpServletRequest request) {

        try {
            User usuarioEnSesion = obtenerUsuarioEnSesion(request);

            if (usuarioEnSesion != null) {
                Map<String, String> response = new HashMap<>();

                if (pedidoService.addLineaPedido(usuarioEnSesion, bebida, idComida, ingredientes)) {
                    response.put("mensaje", "Producto agregado al pedido con éxito.");
                } else {
                    response.put("mensaje", "Ya tienes este producto en tu pedido.");
                }

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse("No se encontró la sesión para el token proporcionado"));
            }
        } catch (Exception e) {
            // Manejar excepciones aquí y devolver una respuesta HTTP apropiada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error en el servidor"));
        }
    }

    @GetMapping("/verificar-pedido")
    public ResponseEntity<Map<String, String>> verificarPedido(HttpServletRequest request) {

        try {
            User usuarioEnSesion = obtenerUsuarioEnSesion(request);

            if (usuarioEnSesion != null) {
                Map<String, String> response = new HashMap<>();

                System.out.println("lineapedidos: " + pedidoService.obtenerLineasPedido(obtenerUsuarioEnSesion(request)).isEmpty());
                if (pedidoService.obtenerLineasPedido(obtenerUsuarioEnSesion(request)).isEmpty()) {

                    response.put("mensaje", "El carrito está vacio");

                } else {
                    response.put("mensaje", "El carrito no está vacio");
                }

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse("No se encontró la sesión para el token proporcionado"));
            }
        } catch (Exception e) {
            // Manejar excepciones aquí y devolver una respuesta HTTP apropiada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Error en el servidor"));
        }
    }

    @PostMapping("/redimir")
    @ResponseBody
    public Map<String, Object> redimirPuntos(HttpServletRequest request, @RequestBody String puntosRedimibles) {
        Map<String, Object> response = new HashMap<>();

        try {
            double montoRedimido = Double.parseDouble(puntosRedimibles);
            // Realiza la lógica de redención de puntos
            tarjetaService.recarga(montoRedimido, LocalDateTime.now(), obtenerUsuarioEnSesion(request).getTarjeta(), "Redencion de puntos");
            tarjetaService.modificarPuntos(obtenerUsuarioEnSesion(request).getTarjeta());

            response.put("success", true);
            response.put("message", "Puntos redimidos correctamente");

        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Error al procesar la redención de puntos");
        }

        return response;
    }

    @PostMapping("/datosPedido")
    @ResponseBody
    public Map<String, Object> actualizarPedido(HttpServletRequest request, @RequestBody Map<String, Object> datos) {

        Map<String, Object> response = new HashMap<>();

        try {
            boolean deseaDomicilio = (boolean) datos.get("deseaDomicilio");
            String fechaRecogidaStr = datos.get("fechaRecogida").toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fechaRecogidaConvertida = LocalDateTime.parse(fechaRecogidaStr, formatter);

            pedidoService.completarDatos(deseaDomicilio, fechaRecogidaConvertida, pedidoService.pedidoActive(obtenerUsuarioEnSesion(request)));

            response.put("success", true);
            response.put("message", "actualizó datos correctamente");

        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Error al actualizar datos");
        }

        return response;
    }

    @PostMapping("/detallePago")
    @ResponseBody
    public ResponseEntity<String> finalizarPago(HttpServletRequest request) {

        try {

            User usuario = obtenerUsuarioEnSesion(request);
            Pedido pedido = pedidoService.pedidoActive(usuario);
            Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUser(usuario);

            double montoPago = pedido.getCostoTotal();
            LocalDateTime fechaPago = LocalDateTime.now();

            Transaccion pago = tarjetaService.paga(montoPago, fechaPago, tarjeta, "pago del pedido # " + pedido.getPedidoId());
            pedido.setPago(pago);
            pedido.setEstado(EstadoPedido.FINALIZADO);

            pedidoService.actualizarPedido(pedido);

            return ResponseEntity.ok("Pago finalizado correctamente");

        } catch (Exception e) {
            // En caso de error, devuelve un código de estado 500 (Internal Server Error)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo completar el pago: " + e.getMessage());
        }
    }

    private User obtenerUsuarioEnSesion(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String authToken = Arrays.stream(cookies)
                    .filter(cookie -> "authToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);

            if (authToken != null) {
                Session session = userService.getSession(authToken);

                if (session != null) {
                    return session.getUser();
                }
            }
        }

        return null;
    }

    private Map<String, String> errorResponse(String mensaje) {
        Map<String, String> response = new HashMap<>();
        response.put("error", mensaje);
        return response;
    }

    private List<LineaPedido> obtenerLineasPedidos(HttpServletRequest request) {
        User usuarioEnSesion = obtenerUsuarioEnSesion(request);
        return (usuarioEnSesion != null) ? pedidoService.obtenerLineasPedido(usuarioEnSesion) : new ArrayList<>();
    }

    private Pedido obtenerPedido(HttpServletRequest request) {
        User usuarioEnSesion = obtenerUsuarioEnSesion(request);
        return (usuarioEnSesion != null) ? pedidoService.pedidoActive(usuarioEnSesion) : null;
    }
}
