package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "DETALLE_COMIDA_INSUMO")
public class DetalleComidaInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalleInsumoId")
    private Long detalleInsumoId;

    @ManyToOne
    @JoinColumn(name = "comida_id")
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    @Column(name = "cantidad")
    private int cantidad;

    public DetalleComidaInsumo() {
    }

    public DetalleComidaInsumo( Long id, int cantidad) {
        this.detalleInsumoId = id;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return detalleInsumoId;
    }

    public void setId(Long id) {
        this.detalleInsumoId = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleComidaInsumo that = (DetalleComidaInsumo) o;
        return cantidad == that.cantidad && Objects.equals(detalleInsumoId, that.detalleInsumoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detalleInsumoId, cantidad);
    }

    @Override
    public String toString() {
        return "DetalleComidaInsumo{" +
                "id=" + detalleInsumoId +
                ", cantidad=" + cantidad +
                '}';
    }
}
