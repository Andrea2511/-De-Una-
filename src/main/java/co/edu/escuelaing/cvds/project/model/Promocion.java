package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PROMOCION")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "descuento")
    private double descuento;

    @Column(name = "fechaInicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fechaFin")
    private LocalDateTime fechaFin;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "cantidadMinimaCompra")
    private int cantidadMinimaCompra;

    @ElementCollection
    @CollectionTable(name = "productos_incluidos", joinColumns = @JoinColumn(name = "promocion_id"))
    @Column(name = "producto")
    private Set<String> productosIncluidos;

    @Column(name = "codigoPromocional")
    private String codigoPromocional;

    @Column(name = "tipoDescuento")
    @Enumerated(EnumType.STRING)
    private TipoDescuento tipoDescuento;

    //relaciones
    @OneToMany(mappedBy = "promocion")
    private Set<LineaPedido> lineasPedidos;

    @OneToMany(mappedBy = "promocion")
    private Set<Comida> comidas;

    @OneToMany(mappedBy = "promocion")
    private Set<Pedido> pedidos;

}


