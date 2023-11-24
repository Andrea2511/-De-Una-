package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PREFERENCIAS_CLIENTE")
public class PreferenciasCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vegetariano")
    private boolean vegetariano;
    @Column(name = "vegano")
    private boolean vegano;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<AlimentoNoConsumido> alimentosNoConsumidos;
    @Enumerated(EnumType.STRING)
    private Temperatura temperatura;


    // Relaciones
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public PreferenciasCliente() {
    }

    public PreferenciasCliente(boolean vegetariano, boolean vegano, Set<AlimentoNoConsumido> alimentosNoConsumidos, Temperatura temperatura) {
        this.vegetariano = vegetariano;
        this.vegano = vegano;
        this.alimentosNoConsumidos = alimentosNoConsumidos;
        this.temperatura = temperatura;
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public boolean isVegano() {
        return vegano;
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    public Set<AlimentoNoConsumido> getAlimentosNoConsumidos() {
        return alimentosNoConsumidos;
    }

    public void setAlimentosNoConsumidos(Set<AlimentoNoConsumido> alimentosNoConsumidos) {
        this.alimentosNoConsumidos = alimentosNoConsumidos;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenciasCliente that = (PreferenciasCliente) o;
        return vegetariano == that.vegetariano && vegano == that.vegano && Objects.equals(id, that.id) && Objects.equals(alimentosNoConsumidos, that.alimentosNoConsumidos) && temperatura == that.temperatura && Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vegetariano, vegano, alimentosNoConsumidos, temperatura, cliente);
    }

    @Override
    public String toString() {
        return "PreferenciasCliente{" +
                "id=" + id +
                ", vegetariano=" + vegetariano +
                ", vegano=" + vegano +
                ", alimentosNoConsumidos=" + alimentosNoConsumidos +
                ", temperatura=" + temperatura +
                ", cliente=" + cliente +
                '}';
    }
}

