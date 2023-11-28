package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @Column(name = "pedidoId")
    private String id;
    @Column(name = "fechaInicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fechaEntrega")
    private LocalDateTime fechaEntrega;
    @Column(name = "costoTotal")
    private double costoTotal;

    @Column(name = "estado")
    private EstadoPedido estado;

    //Relaciones
    @ManyToOne
    private Supervisor supervisor;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<LineaPedido> lineasPedido;
    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;
    @OneToOne
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    public Pedido() {
    }

    public Pedido(String id, LocalDateTime fechaInicio, LocalDateTime fechaEntrega, double costoTotal) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.costoTotal = costoTotal;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Double.compare(costoTotal, pedido.costoTotal) == 0 && Objects.equals(id, pedido.id) && Objects.equals(fechaInicio, pedido.fechaInicio) && Objects.equals(fechaEntrega, pedido.fechaEntrega) && Objects.equals(supervisor, pedido.supervisor) && Objects.equals(lineasPedido, pedido.lineasPedido) && Objects.equals(promocion, pedido.promocion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInicio, fechaEntrega, costoTotal, supervisor, lineasPedido, promocion);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaEntrega=" + fechaEntrega +
                ", costoTotal=" + costoTotal +
                ", supervisor=" + supervisor +
                ", lineasPedido=" + lineasPedido +
                ", promocion=" + promocion +
                '}';
    }
}
