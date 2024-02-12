package nl.bitsentools.eindprojectbackendmetabo.repositories;


import nl.bitsentools.eindprojectbackendmetabo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{}
