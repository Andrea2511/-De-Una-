package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "SUPERVISOR")
public class Supervisor extends User{

    //Relaciones
    @OneToOne(mappedBy = "supervisor")
    private Restaurante restaurante;
    @OneToMany(mappedBy = "supervisor")
    private ArrayList<Pedido> pedidos;

    public Supervisor(){
    }

    public Supervisor(String firstName, String lastName, String username, String password, String email, String rol) {
        super(firstName, lastName, username, password, email);
    }

    @Override
    public String getRol() {
        return "SUPERVISOR";
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
