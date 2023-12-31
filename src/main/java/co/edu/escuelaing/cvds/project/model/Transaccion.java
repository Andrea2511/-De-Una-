package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TRANSACCION")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private TipoTransaccion tipoTransaccion;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "fechapago", nullable = false)
    private LocalDateTime fechaPago;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id")
    private Tarjeta tarjeta;

}
