package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
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

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "costoTotal")
    private double costoTotal;

    @Column(name = "estado")
    private EstadoPedido estado;

    @Column(name = "domicilio")
    private boolean domicilio;

    //Relaciones

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<LineaPedido> lineasPedido;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id", referencedColumnName = "id")
    private Transaccion pago;

}
