package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
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
    public ResponseEntity<List<ProductOutputDto>> getAllProducts() {
        List<ProductOutputDto> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductOutputDto>getOneProduct(@PathVariable("id") Long id){
        ProductOutputDto productOutputDto = productService.getOneProductById(id);

 //hier kan je de exception opgooien
    return ResponseEntity.ok(productOutputDto);
    }

    @PostMapping
    public ResponseEntity<ProductOutputDto> createProduct(@RequestBody ProductInputDto productInputDto) {

    ProductOutputDto savedProduct = productService.createProduct(productInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedProduct.id)
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
        //hier komt straks de ProductService te staan
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}

