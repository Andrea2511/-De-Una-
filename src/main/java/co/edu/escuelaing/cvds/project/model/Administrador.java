package co.edu.escuelaing.cvds.project.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Administrador extends User{

    //Relaciones
    @OneToOne(mappedBy = "administrador")
    private Restaurante restaurante;

    @Override
    public String getRol() {
        return "ADMINISTRADOR";
    }
}
