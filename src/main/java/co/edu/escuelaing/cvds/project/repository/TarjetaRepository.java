package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Promocion;
import co.edu.escuelaing.cvds.project.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Object> {

}