package mp.exemplo2.repositories;

import mp.exemplo2.model.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupermercadoRepository extends JpaRepository<Supermercado, Long> {
}
