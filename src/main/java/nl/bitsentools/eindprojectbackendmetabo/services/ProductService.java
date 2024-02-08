package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.ProductDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.Product;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //GET-all

    public List<Product> getAllProducts(Long id) {
        return productRepository.findAll();

    }

    //GET-byId

    public Optional<Product> getOneProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional;
        } else {
            throw new RecordNotFoundException("product with id :" + id + " not found");
        }
    }


    //POST
    public ProductDto createProduct(ProductDto createProductDto) {
        Product product = new Product();
        product.setId(createProductDto.id);
        product.setBrandName(createProductDto.brandName);
        product.setProductName(createProductDto.productName);
        product.setProductNumber(createProductDto.productNumber);
        product.setPrice(createProductDto.price);
        product.setTypeOfMachine(createProductDto.typeOfMachine);

        productRepository.save(product);
        createProductDto.id = product.getId();
        return createProductDto;
    }

    //PUT

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product excistingProduct = existingProductOptional.get();
            excistingProduct.setId(productDto.id);
            excistingProduct.setBrandName(productDto.brandName);
            excistingProduct.setProductName(productDto.productName);
            excistingProduct.setProductNumber(productDto.productNumber);
            excistingProduct.setPrice(productDto.price);
            excistingProduct.setTypeOfMachine(productDto.typeOfMachine);

            productRepository.save(excistingProduct);
            return productToProductDto(excistingProduct);

        } else {
            throw new RecordNotFoundException("Product with id: " + id + " not found");
        }
    }

    //DELETE

    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("product with id: " + id + " not found");
        }
    }


    // Helper method to convert Product to ProductDto
    public ProductDto productToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.id = product.getId();
        productDto.brandName = product.getBrandName();
        productDto.productName = product.getProductName();
        productDto.productNumber = product.getProductNumber();
        productDto.price = product.getPrice();
        productDto.typeOfMachine = product.getTypeOfMachine();
        return productDto;
    }
}

