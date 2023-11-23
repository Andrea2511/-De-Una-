package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// PagoRepository.java
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Puedes agregar métodos específicos si es necesario
}
