package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "LINEAPEDIDO")
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "total")
    private double total;

    @Column(name = "bebida")
    private String bebida;

    @Column(name = "ingredientespedidos")
    private String ingredientes;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "comida_id")
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

}
