package co.edu.escuelaing.cvds.project.repository;

import co.edu.escuelaing.cvds.project.model.Tarjeta;
import co.edu.escuelaing.cvds.project.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Object> {

    List<Transaccion> findByTarjeta(Tarjeta tarjeta);
}
