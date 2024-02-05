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



    private ArrayList<Product> products = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return new ResponseEntity<>(this.products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        if (product != null) {
            this.products.add(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

//    @PutMapping("/{id"})
//        public ResponseEntity<Product>updateProduct(@PathVariable  Long id, @RequestBody )
//
//}

