package nl.bitsentools.eindprojectbackendmetabo.controllers;

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
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product>getProduct(@PathVariable("id") Long id){
    Product savedProduct = productRepository.getReferenceById(id);
    return ResponseEntity.ok(savedProduct);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        //product toevoegen
        //201 status returnen
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.created(null).body(savedProduct);


    }

//
    @PutMapping("/products/{id}")
    public ResponseEntity<Object>changeProduct(@PathVariable Long id, @RequestBody String product){
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object>deleteProduct(@PathVariable Long id){
        //hier komt straks de ProductService te staan
//
        return ResponseEntity.noContent().build();
    }
}

