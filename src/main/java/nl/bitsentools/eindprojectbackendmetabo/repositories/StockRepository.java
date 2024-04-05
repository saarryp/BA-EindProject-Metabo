
package nl.bitsentools.eindprojectbackendmetabo.repositories;

import nl.bitsentools.eindprojectbackendmetabo.models.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository <StockModel, Long> {
}
