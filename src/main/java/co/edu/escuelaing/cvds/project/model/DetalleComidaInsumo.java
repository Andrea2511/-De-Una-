package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class DetalleComidaInsumo {

    @EmbeddedId
    private DetalleComidaInsumoId id;

    private int cantidad;

    public DetalleComidaInsumo() {
    }

    public DetalleComidaInsumo(DetalleComidaInsumoId id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public DetalleComidaInsumoId getId() {
        return id;
    }

    public void setId(DetalleComidaInsumoId id) {
        this.id = id;
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
        return cantidad == that.cantidad && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad);
    }

    @Override
    public String toString() {
        return "DetalleComidaInsumo{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                '}';
    }
}
