package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TARJETA")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "SaldoTotal",nullable = false)
    private double saldoT;

    @Column(name = "PuntosRedimibles")
    private Integer puntosRedimibles;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaccion> transacciones;

    @OneToOne(mappedBy = "tarjeta")
    private User usuario;

    public Tarjeta(String password1) {
    }
}
