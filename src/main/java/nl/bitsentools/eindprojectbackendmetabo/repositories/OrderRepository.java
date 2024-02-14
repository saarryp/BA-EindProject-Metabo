package nl.bitsentools.eindprojectbackendmetabo.repositories;

import nl.bitsentools.eindprojectbackendmetabo.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
