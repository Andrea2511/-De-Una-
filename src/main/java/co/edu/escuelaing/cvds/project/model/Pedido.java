package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "fechaInicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fechaEntrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "costoTotal")
    private double costoTotal;

    @Column(name = "estado")
    private EstadoPedido estado;

    //Relaciones

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<LineaPedido> lineasPedido;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    @OneToOne
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

}
