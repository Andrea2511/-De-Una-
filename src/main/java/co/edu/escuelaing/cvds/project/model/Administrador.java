package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador extends User{

    //Relaciones
    @OneToOne(mappedBy = "administrador")
    private Restaurante restaurante;

    public Administrador(){
    }

    public Administrador(String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
    }

    @Override
    public String getRol() {
        return "ADMINISTRADOR";
    }
}
