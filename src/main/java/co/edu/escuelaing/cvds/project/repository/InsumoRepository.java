package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Insumo;
import co.edu.escuelaing.cvds.project.model.TipoInsumos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByTipoOrderByNombre(TipoInsumos tipoInsumos);

    // Puedes agregar métodos de consulta personalizados aquí si es necesario.
}
