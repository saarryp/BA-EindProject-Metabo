package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDtoWarranty;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.OrderRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static nl.bitsentools.eindprojectbackendmetabo.mapper.ProductMapper.transferToProductDto;
import static nl.bitsentools.eindprojectbackendmetabo.mapper.ProductMapper.transferToProduct;

@Service
public class ProductService {
    private final ProductRepository productRepository;
//  private final WarrantyRepository warrantyRepository;
    private final OrderRepository orderRepository;


    public ProductService(ProductRepository productRepository, OrderRepository orderRepository, OrderRepository orderRepository1) {

        this.productRepository = productRepository;
//      this.warrantyRepository = warrantyRepository;
        this.orderRepository = orderRepository;
    }


    //GET-all

    public List<Object> getAllProducts() {
        List<ProductModel>productList = productRepository.findAll();
        List<Object> productOutputDtoList = new ArrayList<>();
        for(ProductModel product : productList) {
            if (product.isProductWarranty()){
                productOutputDtoList.add(transferToDtoWarranty(product));
            }else {
                productOutputDtoList.add(transferToProductDto(product));
            }
        }
        return productOutputDtoList;
    }

    //GET-byId

    public ProductOutputDto getOneProductById(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return transferToProductDto(productOptional.get());
        } else {
            throw new RecordNotFoundException("product with id :" + id + " not found");
        }
    }

    //POST



    public Object createProduct(ProductInputDto createProductDto) {
        ProductModel product = transferToProduct(createProductDto);

        productRepository.save(product);

        if (product.isProductWarranty()){
            return transferToDtoWarranty(product);
        }
        else {
            return transferToProductDto(product);
        }
    }

    //afbeeldingen toevoegen
    public void uploadDefaultImage(Long productId, MultipartFile file) throws IOException {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Product not found"));

        // Convert the image to Base64
        String base64Image = convertImageToBase64(file);

        // Set the Base64 image to the product
        product.setDefaultImageBase64(base64Image);

        // Save the updated product
        productRepository.save(product);
    }

    private String convertImageToBase64(MultipartFile file) throws IOException, IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }


    //PUT

    public ProductOutputDto updateProduct(Long id, ProductInputDto productDto) {
        Optional<ProductModel> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            ProductModel excistingProduct = existingProductOptional.get();
//            excistingProduct.setId(productDto.id);
            excistingProduct.setBrandName(productDto.brandName);
            excistingProduct.setProductName(productDto.productName);
            excistingProduct.setProductNumber(productDto.productNumber);
            excistingProduct.setPrice(productDto.price);
            excistingProduct.setTypeOfMachine(productDto.typeOfMachine);


            productRepository.save(excistingProduct);
            return transferToProductDto(excistingProduct);

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

    // Dit is de vertaal methode van productInputDto naar product.

    //twee helperfuncties maken van inputdto naar model en van model naar outputdto


    // Dit is de vertaal methode van Product naar ProductDto


    public static ProductOutputDtoWarranty transferToDtoWarranty(ProductModel product){
        ProductOutputDtoWarranty dto = new ProductOutputDtoWarranty();

        //deze omzetten naar product

        dto.setId(product.getId());
        dto.setBrandName(product.getBrandName());
        dto.setProductName(product.getProductName());
        dto.setProductNumber(product.getProductNumber());
        dto.setPrice(product.getPrice());
        dto.setTypeOfMachine(product.getTypeOfMachine());
        dto.setWarranty(product.isProductWarranty());
        dto.setWarrantyInMonths(product.getWarrantyInMonths());
        dto.setDefaultImageBase64(product.getDefaultImageBase64());


        return dto;
    }

}


