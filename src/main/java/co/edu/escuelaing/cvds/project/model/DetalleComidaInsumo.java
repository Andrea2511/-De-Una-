package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "DETALLE_COMIDA_INSUMO")
public class DetalleComidaInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalleInsumoId")
    private Long detalleInsumoId;

    @ManyToOne
    @JoinColumn(name = "comida_id")
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    @Column(name = "cantidad")
    private int cantidad;

}
