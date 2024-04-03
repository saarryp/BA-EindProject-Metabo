package nl.bitsentools.eindprojectbackendmetabo.repositories;

import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long>{
    Optional<InvoiceModel> findByIdAndUserId(Long id, Long userId);
}
