package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.ImageData;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import nl.bitsentools.eindprojectbackendmetabo.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;


public class ProductOutputDto {

    public Long id;

    public String brandName;

    public String productName;

    public int productNumber;

    public double price;

    @Enumerated(EnumType.STRING)
    public TypeOfMachine typeOfMachine;

    public List<byte[]> imageData;

    public ProductOutputDto() {
    }

    public List<byte[]> getImageData() {
        return imageData;
    }

    public void setImageData(List<ImageData> imageData) {
        List<byte[]> imgbase64 = new ArrayList<>();
        for (ImageData ig : imageData){
            imgbase64.add(ImageUtil.decompressImage(ig.getImageData()));
        }
        this.imageData = imgbase64;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypeOfMachine getTypeOfMachine() {
        return typeOfMachine;
    }

    public void setTypeOfMachine(TypeOfMachine typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
    }


}
