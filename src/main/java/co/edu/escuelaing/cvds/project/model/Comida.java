package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "COMIDA")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comidaId")
    private Long comidaId;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private Categoria categoria;

    @Column(name = "calificacion")
    private double calificacion;
    @Lob
    @Column(name = "ruta", length = 1048576)
    private String ruta;
    @Column(name = "precio")
    private double precio;

    @Column(name = "cantidad")
    private int cantidad;

    @OneToMany(mappedBy = "comida")
    private Set<DetalleComidaInsumo> detalleInsumos = new HashSet<>();

    @OneToMany(mappedBy = "comida")
    private Set<LineaPedido> lineasPedidos;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    public Comida() {
    }

    public Comida(String nombre, double calificacion, double precio, Set<DetalleComidaInsumo> detalleInsumos) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.precio = precio;
    }

    public Long getComidaId() {
        return comidaId;
    }

    public void setComidaId(Long insumoId) {
        this.comidaId = insumoId;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<DetalleComidaInsumo> getDetalleInsumos() {
        return detalleInsumos;
    }

    public void setDetalleInsumos(Set<DetalleComidaInsumo> detalleInsumos) {
        this.detalleInsumos = detalleInsumos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comida comida = (Comida) o;
        return Double.compare(calificacion, comida.calificacion) == 0 && Double.compare(precio, comida.precio) == 0 && Objects.equals(comidaId, comida.comidaId) && Objects.equals(nombre, comida.nombre) && Objects.equals(ruta, comida.ruta) && Objects.equals(detalleInsumos, comida.detalleInsumos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comidaId, nombre, calificacion, ruta, precio, detalleInsumos);
    }

    @Override
    public String toString() {
        return "Comida{" +
                "insumoId=" + comidaId +
                ", nombre='" + nombre + '\'' +
                ", calificacion=" + calificacion +
                ", ruta=" + ruta +
                ", precio=" + precio +
                ", detalleInsumos=" + detalleInsumos +
                '}';
    }



}
