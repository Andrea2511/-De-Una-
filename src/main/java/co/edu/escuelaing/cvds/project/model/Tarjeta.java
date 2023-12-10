package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.*;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "SaldoTotal",nullable = false)
    private Integer saldoT;

    @Column(name = "PuntosRedimibles")
    private Integer puntosRedimibles;

    @Column(name = "password", nullable = false)
    private String password;

    public Tarjeta() {
    }

    public Tarjeta(String password) {
        this.password = password;
        this.puntosRedimibles = 0;
        this.saldoT = 0;
    }

    public Long getId() {
        return id;
    }

    public Integer getSaldoT() {
        return saldoT;
    }

    public Integer getPuntosRedimibles() {
        return puntosRedimibles;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public void setSaldoT(Integer saldoT) {
        this.saldoT = saldoT;
    }

    public void setPuntosRedimibles(Integer puntosRedimibles) {
        this.puntosRedimibles = puntosRedimibles;
    }

    public void redimirPuntos() {
        Integer puntosAredimir = (puntosRedimibles / 100) ;
        this.puntosRedimibles = puntosRedimibles - (puntosAredimir * 100);
        Integer saldo = puntosAredimir * 2000;
        if(saldo > 0){
            setSaldoT(saldoT + saldo);
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
