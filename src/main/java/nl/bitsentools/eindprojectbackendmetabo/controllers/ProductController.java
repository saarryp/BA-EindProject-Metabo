package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.Product;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductOutputDto>> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return new ResponseEntity<>(products, HttpStatus.OK);
        return ResponseEntity.ok(productService.getAllProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product>getOneProduct(@PathVariable("id") Long id){
    Product savedProduct = productRepository.getReferenceById(id);

 //hier kan je de exception opgooien
    return ResponseEntity.ok(savedProduct);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        //product toevoegen
        //201 status returnen
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.created(null).body(savedProduct);


    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object>updateProduct(@PathVariable Long id, @RequestBody String product){
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object>deleteProduct(@PathVariable Long id){
        //hier komt straks de ProductService te staan
//
        return ResponseEntity.noContent().build();
    }
}

