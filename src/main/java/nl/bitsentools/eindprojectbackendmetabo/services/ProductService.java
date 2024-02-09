package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.Product;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //GET-all

    public List<ProductOutputDto> getAllProducts() {
        List<Product>productList = productRepository.findAll();
        List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
        for(Product product : productList) {
            productOutputDtoList.add(productToProductDto(product));
        }
        return productOutputDtoList;

    }

    //GET-byId

    public ProductOutputDto getOneProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productToProductDto(productOptional.get());
        } else {
            throw new RecordNotFoundException("product with id :" + id + " not found");
        }
    }



    //POST
    public ProductOutputDto createProduct(ProductInputDto createProductDto) {
        Product product = new Product();
        product.setId(createProductDto.id);
        product.setBrandName(createProductDto.brandName);
        product.setProductName(createProductDto.productName);
        product.setProductNumber(createProductDto.productNumber);
        product.setPrice(createProductDto.price);
        product.setTypeOfMachine(createProductDto.typeOfMachine);

        productRepository.save(product);

        return productToProductDto(product);
    }

    //PUT

    public ProductOutputDto updateProduct(Long id, ProductInputDto productDto) {
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
//    public ProductOutputDto productToProductOutputDto(Product product) {
//        ProductInputDto productInputDto = new ProductInputDto();
//        productO.id = product.getId();
//        productDto.brandName = product.getBrandName();
//        productDto.productName = product.getProductName();
//        productDto.productNumber = product.getProductNumber();
//        productDto.price = product.getPrice();
//        productDto.typeOfMachine = product.getTypeOfMachine();
//        return ProductOutputDto;
//    }
    // Dit is de vertaal methode van productInputDto naar product.

    //twee helperfuncties maken van inputdto naar model en van model naar outputdto
    public Product transferToProduct(ProductInputDto dto){
        var product = new Product();

        product.setId(dto.id);
        product.setBrandName(dto.brandName);
        product.setProductName(dto.productName);
        product.setProductNumber(dto.productNumber);
        product.setPrice(dto.price);
        product.setTypeOfMachine(dto.typeOfMachine);

        return product;
    }

    // Dit is de vertaal methode van Product naar ProductDto
    public ProductOutputDto transferToDto(Product product){
       ProductOutputDto dto = new ProductOutputDto();

        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getWifi());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        return dto;
    }
}


