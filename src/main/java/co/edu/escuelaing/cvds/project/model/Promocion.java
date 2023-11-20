package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PROMOCION")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "descuento")
    private double descuento;
    @Column(name = "fechaInicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fechaFin")
    private LocalDateTime fechaFin;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "cantidadMinimaCompra")
    private int cantidadMinimaCompra;
    @ElementCollection
    @CollectionTable(name = "productos_incluidos", joinColumns = @JoinColumn(name = "promocion_id"))
    @Column(name = "producto")
    private Set<String> productosIncluidos;
    @Column(name = "codigoPromocional")
    private String codigoPromocional;
    @Column(name = "tipoDescuento")
    @Enumerated(EnumType.STRING)
    private TipoDescuento tipoDescuento;

    //relaciones
    @OneToMany(mappedBy = "promocion")
    private Set<LineaPedido> lineasPedidos;
    @OneToMany(mappedBy = "promocion")
    private Set<Comida> comidas;
    @OneToMany(mappedBy = "promocion")
    private Set<Pedido> pedidos;
    @OneToMany(mappedBy = "promocion")
    private Set<Domicilio> domicilios;

    public Promocion() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadMinimaCompra() {
        return cantidadMinimaCompra;
    }

    public void setCantidadMinimaCompra(int cantidadMinimaCompra) {
        this.cantidadMinimaCompra = cantidadMinimaCompra;
    }

    public Set<String> getProductosIncluidos() {
        return productosIncluidos;
    }

    public void setProductosIncluidos(Set<String> productosIncluidos) {
        this.productosIncluidos = productosIncluidos;
    }

    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    public void setCodigoPromocional(String codigoPromocional) {
        this.codigoPromocional = codigoPromocional;
    }

    public TipoDescuento getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public Set<LineaPedido> getLineasPedidos() {
        return lineasPedidos;
    }

    public void setLineasPedidos(Set<LineaPedido> lineasPedidos) {
        this.lineasPedidos = lineasPedidos;
    }

    public Set<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(Set<Comida> comidas) {
        this.comidas = comidas;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(Set<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promocion promocion = (Promocion) o;
        return Double.compare(descuento, promocion.descuento) == 0 && cantidadMinimaCompra == promocion.cantidadMinimaCompra && Objects.equals(id, promocion.id) && Objects.equals(nombre, promocion.nombre) && Objects.equals(descripcion, promocion.descripcion) && Objects.equals(fechaInicio, promocion.fechaInicio) && Objects.equals(fechaFin, promocion.fechaFin) && Objects.equals(categoria, promocion.categoria) && Objects.equals(productosIncluidos, promocion.productosIncluidos) && Objects.equals(codigoPromocional, promocion.codigoPromocional) && tipoDescuento == promocion.tipoDescuento && Objects.equals(lineasPedidos, promocion.lineasPedidos) && Objects.equals(comidas, promocion.comidas) && Objects.equals(pedidos, promocion.pedidos) && Objects.equals(domicilios, promocion.domicilios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, descuento, fechaInicio, fechaFin, categoria, cantidadMinimaCompra, productosIncluidos, codigoPromocional, tipoDescuento, lineasPedidos, comidas, pedidos, domicilios);
    }

    @Override
    public String toString() {
        return "Promocion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", descuento=" + descuento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", categoria='" + categoria + '\'' +
                ", cantidadMinimaCompra=" + cantidadMinimaCompra +
                ", productosIncluidos=" + productosIncluidos +
                ", codigoPromocional='" + codigoPromocional + '\'' +
                ", tipoDescuento=" + tipoDescuento +
                ", lineasPedidos=" + lineasPedidos +
                ", comidas=" + comidas +
                ", pedidos=" + pedidos +
                ", domicilios=" + domicilios +
                '}';
    }
}

