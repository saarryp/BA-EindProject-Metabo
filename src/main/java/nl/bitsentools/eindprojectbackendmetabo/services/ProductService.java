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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
//  private final WarrantyRepository warrantyRepository;
    private final OrderRepository orderRepository;


    public ProductService(ProductRepository productRepository, OrderRepository orderRepository, OrderRepository orderRepository1) {

        this.productRepository = productRepository;
//      this.warrantyRepository = warrantyRepository;
        this.orderRepository = orderRepository1;
    }


    //GET-all

    public List<Object> getAllProducts() {
        List<ProductModel>productList = productRepository.findAll();
        List<Object> productOutputDtoList = new ArrayList<>();
        for(ProductModel product : productList) {
            if (product.isProductWarranty()){
                productOutputDtoList.add(transferToDtoWarranty(product));
            }else {
                productOutputDtoList.add(transferToDto(product));
            }
        }
        return productOutputDtoList;
    }

    //GET-byId

    public ProductOutputDto getOneProductById(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return transferToDto(productOptional.get());
        } else {
            throw new RecordNotFoundException("product with id :" + id + " not found");
        }
    }

    //POST



    public Object createProduct(ProductInputDto createProductDto) {
        ProductModel product = transferToProduct(createProductDto);

        //bepaalt of er garantie op zit
//        if(true) {
//            WarrantyModel warranty = createWarrantyForProduct(product);
//            product.setWarrantyModel(warranty);
//        }

        productRepository.save(product);

        if (product.isProductWarranty()){
            return transferToDtoWarranty(product);
        }
        else {
            return transferToDto(product);
        }

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
            return transferToDto(excistingProduct);

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
    public ProductModel transferToProduct(ProductInputDto dto){
        var product = new ProductModel();

//        product.setId(dto.id);
        product.setBrandName(dto.brandName);
        product.setProductName(dto.productName);
        product.setProductNumber(dto.productNumber);
        product.setPrice(dto.price);
        product.setTypeOfMachine(dto.typeOfMachine);
        product.setProductWarranty((dto.productWarranty));
        if (product.isProductWarranty()) {
            product.setWarrantyInMonths((dto.warrantyInMonths));
        }
        else {
            product.setWarrantyInMonths(0);
        }
        return product;
    }

    // Dit is de vertaal methode van Product naar ProductDto
    public ProductOutputDto transferToDto(ProductModel product){
       ProductOutputDto dto = new ProductOutputDto();

       //deze omzetten naar product

        dto.setId(product.getId());
        dto.setBrandName(product.getBrandName());
        dto.setProductName(product.getProductName());
        dto.setProductNumber(product.getProductNumber());
        dto.setPrice(product.getPrice());
        dto.setTypeOfMachine(product.getTypeOfMachine());


        return dto;
    }

    public ProductOutputDtoWarranty transferToDtoWarranty(ProductModel product){
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


        return dto;
    }

}


