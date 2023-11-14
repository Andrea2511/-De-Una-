package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "COMIDA")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comidaId")
    private Long insumoId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "calificacion")
    private double calificacion;
    @Column(name = "foto")
    private ImageIcon foto;
    @Column(name = "precio")
    private double precio;
    @OneToMany(mappedBy = "comida")
    private ArrayList<DetalleComidaInsumo> detalleInsumos;
    @OneToMany(mappedBy = "comida")
    private ArrayList<LineaPedido> lineasPedidos;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    public Comida() {
    }

    public Comida(Long insumoId, String nombre, double calificacion, ImageIcon foto, double precio, ArrayList<DetalleComidaInsumo> detalleInsumos) {
        this.insumoId = insumoId;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.foto = foto;
        this.precio = precio;
        this.detalleInsumos = detalleInsumos;
    }

    public Long getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Long insumoId) {
        this.insumoId = insumoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<DetalleComidaInsumo> getDetalleInsumos() {
        return detalleInsumos;
    }

    public void setDetalleInsumos(ArrayList<DetalleComidaInsumo> detalleInsumos) {
        this.detalleInsumos = detalleInsumos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comida comida = (Comida) o;
        return Double.compare(calificacion, comida.calificacion) == 0 && Double.compare(precio, comida.precio) == 0 && Objects.equals(insumoId, comida.insumoId) && Objects.equals(nombre, comida.nombre) && Objects.equals(foto, comida.foto) && Objects.equals(detalleInsumos, comida.detalleInsumos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insumoId, nombre, calificacion, foto, precio, detalleInsumos);
    }

    @Override
    public String toString() {
        return "Comida{" +
                "insumoId=" + insumoId +
                ", nombre='" + nombre + '\'' +
                ", calificacion=" + calificacion +
                ", foto=" + foto +
                ", precio=" + precio +
                ", detalleInsumos=" + detalleInsumos +
                '}';
    }
}
