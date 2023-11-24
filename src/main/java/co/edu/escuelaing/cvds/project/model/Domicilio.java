package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DOMICILIO")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "costo")
    private double costo;
    @Column(name = "estado")
    private EstadoDomicilio estado;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;
    @OneToOne(mappedBy = "domicilio")
    private Pedido pedido;

    public Domicilio() {
    }

    public Domicilio(String direccion, double costo, EstadoDomicilio estado) {
        this.direccion = direccion;
        this.costo = costo;
        this.estado = estado;

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public EstadoDomicilio getEstado() {
        return estado;
    }

    public void setEstado(EstadoDomicilio estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domicilio domicilio = (Domicilio) o;
        return Double.compare(costo, domicilio.costo) == 0 && Objects.equals(id, domicilio.id) && Objects.equals(direccion, domicilio.direccion) && estado == domicilio.estado && Objects.equals(promocion, domicilio.promocion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, direccion, costo, estado, promocion);
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", costo=" + costo +
                ", estado=" + estado +
                ", promocion=" + promocion +
                '}';
    }
}
