package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends User{
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;

    //relaciones
    @OneToMany(mappedBy = "cliente")
    private HashSet<Pedido> pedidos;

    @Override
    public String getRol() {
        return "CLIENTE";
    }
}
