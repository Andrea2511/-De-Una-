package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "COMIDA")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comidaId")
    private Long comidaId;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "categoria" , nullable=false)
    private Categoria categoria;

    @Column(name = "calificacion", nullable=false)
    private double calificacion;

    @Lob
    @Column(name = "ruta", length = 1048576)
    private String ruta;

    @Column(name = "precio", nullable=false)
    private double precio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "vegano")
    private boolean vegano;

    @Column(name = "vegetariano")
    private boolean vegetariano;

    @OneToMany(mappedBy = "comida")
    private Set<DetalleComidaInsumo> detalleInsumos = new HashSet<>();

    @OneToMany(mappedBy = "comida")
    private Set<LineaPedido> lineasPedidos;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

}
