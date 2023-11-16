package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Random;
import java.util.Objects;

@Entity
@Table(name = "INSUMO")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insumoId")
    private Long insumoId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fechaVencimiento")
    private Date fechaVencimiento;
    @Column(name = "cantidad")
    private String cantidad;
    @Column(name = "precio")
    private String precio;


    public Insumo() {
    }

    public Insumo(String nombre, String tipo, String cantidad, String precio, Date fechaVencimiento) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaVencimiento = fechaVencimiento;
    }


    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insumo insumo = (Insumo) o;
        return Objects.equals(insumoId, insumo.insumoId) && Objects.equals(nombre, insumo.nombre) && Objects.equals(tipo, insumo.tipo) && Objects.equals(fechaVencimiento, insumo.fechaVencimiento) && Objects.equals(cantidad, insumo.cantidad) && Objects.equals(precio, insumo.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insumoId, nombre, tipo, fechaVencimiento, cantidad, precio);
    }

    @Override
    public String toString() {
        return "Insumo{" +
                "insumoId=" + insumoId +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }

}
