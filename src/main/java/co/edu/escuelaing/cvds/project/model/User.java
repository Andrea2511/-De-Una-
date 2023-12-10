package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "USUARIO")
public class User {

    @Id
    @Column(name = "username", nullable=false, unique=true)
    private String username;

    @Lob
    @Column(name = "ruta", length = 1048576)
    private String ruta;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Pedido> pedidos;

    @ManyToOne
    @JoinColumn(name = "restaurante_nit")
    private Restaurante restaurante;

    @OneToOne
    @JoinColumn(name = "tarjeta")
    private Tarjeta tarjeta;

}