package co.edu.escuelaing.cvds.project.repository;
import co.edu.escuelaing.cvds.project.model.Insumo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    // Puedes agregar métodos de consulta personalizados aquí si es necesario.
}
