package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("SUPERVISOR")
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
