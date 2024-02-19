package nl.bitsentools.eindprojectbackendmetabo.repositories;

import nl.bitsentools.eindprojectbackendmetabo.models.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long>{
}
