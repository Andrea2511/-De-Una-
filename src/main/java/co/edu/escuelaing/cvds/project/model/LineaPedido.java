package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "LINEAPEDIDO")
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "total")
    private double total;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "comida_id")
    private Comida comida;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    public LineaPedido() {
    }

    public LineaPedido(int cantidad, Comida comida, double subtotal, double total) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComidas(Comida comidas) {
        this.comida = comidas;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaPedido that = (LineaPedido) o;
        return cantidad == that.cantidad && Double.compare(subtotal, that.subtotal) == 0 && Double.compare(total, that.total) == 0 && Objects.equals(id, that.id) && Objects.equals(comida, that.comida) && Objects.equals(pedido, that.pedido) && Objects.equals(promocion, that.promocion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, subtotal, total, comida, pedido, promocion);
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", comidas=" + comida +
                ", pedido=" + pedido +
                ", promocion=" + promocion +
                '}';
    }
}
