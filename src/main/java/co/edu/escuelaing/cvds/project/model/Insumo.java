package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "INSUMO")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insumoId")
    private Long insumoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private TipoInsumos tipo;

    @Column(name = "fechaVencimiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaVencimiento;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio")
    private double precio;
}
