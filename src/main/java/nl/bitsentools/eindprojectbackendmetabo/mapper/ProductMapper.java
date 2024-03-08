package nl.bitsentools.eindprojectbackendmetabo.mapper;

import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.product.ProductOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;

public class ProductMapper {
    public static ProductModel transferToProduct(ProductInputDto dto){
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

    public static ProductOutputDto transferToProductDto(ProductModel product){
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
