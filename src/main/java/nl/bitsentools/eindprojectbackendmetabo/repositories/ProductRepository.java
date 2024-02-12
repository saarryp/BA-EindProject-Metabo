package nl.bitsentools.eindprojectbackendmetabo.repositories;


import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long>{}
