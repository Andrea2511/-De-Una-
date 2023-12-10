package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

// Pago.java
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Otros campos relevantes para tu aplicación

    // Getters y setters
}
