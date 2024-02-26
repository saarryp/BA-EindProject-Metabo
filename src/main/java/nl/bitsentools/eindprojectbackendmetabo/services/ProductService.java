package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.warranty.WarrantyInputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.models.WarrantyModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.WarrantyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final WarrantyRepository warrantyRepository;


    public ProductService(ProductRepository productRepository, WarrantyRepository warrantyRepository) {

        this.productRepository = productRepository;
        this.warrantyRepository = warrantyRepository;
    }

    public ProductOutputDto relationWarrantyProduct(Long idProduct, Long idWarranty){
//        ProductOutputDto dto = new ProductOutputDto();

        Optional<ProductModel>productModelOptional = productRepository.findById(idProduct);
        Optional<WarrantyModel>warrantyModelOptional = warrantyRepository.findById(idWarranty);

        if(productModelOptional.isPresent() && warrantyModelOptional.isPresent()) {
            ProductModel product = productModelOptional.get();
            WarrantyModel warranty = warrantyModelOptional.get();

           ProductModel updatedProduct = new ProductModel(warranty);

            productRepository.save(product);
            warrantyRepository.save(warranty);

            return transferToDto(product);
        }else {
            throw new RecordNotFoundException("Product or warranty with provided id's is not found");
        }

        //Retrieve Product from PruductID through the ProductRepository
        //Do the same for warranty
        //Don't forget to retreive Optionals, for both
        //Warranty.setProfile(profile)
        //profile.setWarranty(warranty)
        //repository.save(profile\
        //repository.save(warranty
//        return dto;
    }


    //GET-all

    public List<ProductOutputDto> getAllProducts() {
        List<ProductModel>productList = productRepository.findAll();
        List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
        for(ProductModel product : productList) {
            productOutputDtoList.add(transferToDto(product));
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



    public ProductOutputDto createProduct(ProductInputDto createProductDto, WarrantyInputDto warrantyInputDto) {
        ProductModel product = transferToProduct(createProductDto);

        //bepaalt of er garantie op zit
        if(warrantyInputDto.isProductWarranty()) {
            WarrantyModel warranty = createWarrantyForProduct(product, warrantyInputDto);
            product.setWarrantyModel(warranty);
        }

        productRepository.save(product);
        return transferToDto(product);
    }


    private WarrantyModel createWarrantyForProduct(ProductModel product, WarrantyInputDto warrantyInputDto) {
        WarrantyModel warranty = new WarrantyModel();
        // Instellingen voor garantiegegevens vanuit WarrantyInputDto
        warranty.setProductModel(product);
        warranty.setWarrantyInMonths(warrantyInputDto.getWarrantyInMoths());
        warranty.setWarrantyStart(warrantyInputDto.getWarrantyStart());
        warranty.setWarrantyEnds(warrantyInputDto.getWarrantyEnds());
        warrantyRepository.save(warranty);
        return warranty;
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
}


