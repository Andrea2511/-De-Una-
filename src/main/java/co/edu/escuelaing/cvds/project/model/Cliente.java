package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente extends User{
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    public Cliente() {
        super();
    }

    //relaciones
    @OneToOne(mappedBy = "cliente")
    private PreferenciasCliente preferenciasCliente;

    public Cliente(String firstName, String lastName, String email, String username, String password) {
        super(firstName, lastName, email, username, password);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String getRol() {
        return "CLIENTE";
    }
}
