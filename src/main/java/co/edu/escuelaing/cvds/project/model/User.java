package co.edu.escuelaing.cvds.project.model;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "usuarios")
public abstract class User {

    @Id
    @Column(name = "username", nullable=false, unique=true)
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", nullable=false, unique=true)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "roles", nullable=false)
    private List<Rol> roles;

}