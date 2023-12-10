package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    @Id
    @Column(name = "NIT")
    private String nit;

    @Column(name = "nombre")
    private String nombre;

    //Relaciones
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventario")
    private ArrayList<Insumo> inventario;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<User> usuarios;
}
