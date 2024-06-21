package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.ImageData;
import nl.bitsentools.eindprojectbackendmetabo.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllProducts() {
        List<Object> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductOutputDto>getOneProduct(@PathVariable("id") Long id){
        ProductOutputDto productOutputDto = productService.getOneProductById(id);
    return ResponseEntity.ok(productOutputDto);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductInputDto productInputDto) {

    Object savedProduct = productService.createProduct(productInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedProduct)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedProduct);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOutputDto>updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductInputDto updateProduct ){

        ProductOutputDto dto = productService.updateProduct(id, updateProduct);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{id}/images")
//    public ResponseEntity<List<ImageData>> getAllImagesByProductId(@PathVariable Long id) {
//        List<ImageData> images = productService.getAllImagesByProductId(id);
//        return ResponseEntity.ok(images);
//    }
}




