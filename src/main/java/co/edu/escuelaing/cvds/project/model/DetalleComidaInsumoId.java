package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleComidaInsumoId implements Serializable {

    @ManyToOne
    private Comida comida;

    @ManyToOne
    private Insumo insumo;

    public DetalleComidaInsumoId() {
    }

    public DetalleComidaInsumoId(Comida comida, Insumo insumo) {
        this.comida = comida;
        this.insumo = insumo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleComidaInsumoId that = (DetalleComidaInsumoId) o;
        return Objects.equals(comida, that.comida) && Objects.equals(insumo, that.insumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comida, insumo);
    }

    @Override
    public String toString() {
        return "DetalleComidaInsumoId{" +
                "comida=" + comida +
                ", insumo=" + insumo +
                '}';
    }
}

