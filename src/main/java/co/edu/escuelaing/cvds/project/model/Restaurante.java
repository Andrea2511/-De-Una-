package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    @Id
    @Column(name = "NIT")
    private String nit;
    @Column(name = "nombre")
    private String nombre;

    //Relaciones
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventario")
    private ArrayList<Insumo> inventario;
    @OneToOne
    @JoinColumn(name = "administrador")
    private Administrador admin;
    @OneToOne
    @JoinColumn(name = "supervisor")
    private Administrador supervisor;

    public Restaurante(){
    }

    public Restaurante(String nit, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
