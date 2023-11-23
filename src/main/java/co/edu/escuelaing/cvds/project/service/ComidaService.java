package co.edu.escuelaing.cvds.project.service;

import co.edu.escuelaing.cvds.project.model.Categoria;
import co.edu.escuelaing.cvds.project.model.Comida;
import co.edu.escuelaing.cvds.project.model.DetalleComidaInsumo;
import co.edu.escuelaing.cvds.project.model.Promocion;
import co.edu.escuelaing.cvds.project.repository.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    public ArrayList<Comida> obtenerComidasPorCategoria(Categoria categoria) {
        // Implementa la lógica para obtener comidas por categoría desde el repositorio
        return comidaRepository.findByCategoriaOrderByNombre(categoria);
    }

    public ArrayList<Comida> obtenerMejoresCalificados() {
        // Implementa la lógica para obtener comidas mejor calificadas desde el repositorio
        return comidaRepository.findTop5ByOrderByCalificacionDesc();
    }

    public ArrayList<Comida> obtenerPromociones() {
        // Implementa la lógica para obtener comidas en promoción desde el repositorio
        return comidaRepository.findByPromocionIsNotNull();
    }

    public ArrayList<Comida> obtenerTodasLasComidas() {
        return (ArrayList<Comida>) comidaRepository.findAll();
    }

    public ArrayList<Comida> obtenerComidasConPromocion() {
        return (ArrayList<Comida>) comidaRepository.findByPromocionIsNotNull();
    }

    public Comida crearComida(String nombre, double calificacion, double precio, int cantidad, Categoria categoria){

        DetalleComidaInsumo detalleComidaInsumo = new DetalleComidaInsumo();
        detalleComidaInsumo.setCantidad(2);

        Set<DetalleComidaInsumo> detalleComidaInsumos = new HashSet<>();
        detalleComidaInsumos.add(detalleComidaInsumo);

        Comida comida = new Comida();
        comida.setNombre(nombre);
        comida.setCalificacion(calificacion);
        comida.setPrecio(precio);
        comida.setDetalleInsumos(detalleComidaInsumos);
        comida.setCantidad(cantidad);
        comida.setCategoria(categoria);

        return comida;
    }

    public void guardarComida(Comida comida) {
        // Lógica para guardar el producto en la base de datos
        comidaRepository.save(comida);
    }
    public void actualizarComida(Long comidaId, String nombre, double precio, int cantidad) {

        Comida comidaExistente = comidaRepository.findById(comidaId)
                .orElseThrow(() -> new IllegalArgumentException("Comida no encontrada con ID: " + comidaId));

        // Actualizar los datos con los valores editados
        comidaExistente.setNombre(nombre);
        comidaExistente.setPrecio(precio);
        comidaExistente.setCantidad(cantidad);

        // Guardar la comida actualizada en la base de datos
        comidaRepository.save(comidaExistente);

    }

    public void eliminarComida(Long comidaId) {

        Comida comidaExistente = comidaRepository.findById(comidaId)
                .orElseThrow(() -> new IllegalArgumentException("Comida no encontrada con ID: " + comidaId));

        // Elimina
        comidaRepository.delete(comidaExistente);

    }
}
