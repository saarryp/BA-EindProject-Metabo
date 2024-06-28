package nl.bitsentools.eindprojectbackendmetabo.repositories;


import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long>{
    Optional<ProductModel> findByProductNumber(int productNumber);
}
