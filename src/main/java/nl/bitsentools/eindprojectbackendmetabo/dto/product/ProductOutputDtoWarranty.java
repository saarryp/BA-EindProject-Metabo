package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.ImageData;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import nl.bitsentools.eindprojectbackendmetabo.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductOutputDtoWarranty {

    public Long id;

    public String brandName;

    public String productName;

    public Integer productNumber;

    public Double price;
    public boolean hasWarranty;

    public Integer warrantyInMonths;

    @Enumerated(EnumType.STRING)
    public TypeOfMachine typeOfMachine;

    public List<byte[]> imageData;

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

    public ProductOutputDtoWarranty(){}

    public Long getId() {
            return id;
        }


    public ProductOutputDtoWarranty(Long id, String brandName, String productName, int productNumber, double price, boolean hasWarranty,
                                    int warrantyInMonths, TypeOfMachine typeOfMachine, String defaultImageBase64) {
        this.id = id;
        this.brandName = brandName;
        this.productName = productName;
        this.productNumber = productNumber;
        this.price = price;
        this.hasWarranty = hasWarranty;
        this.warrantyInMonths = warrantyInMonths;
        this.typeOfMachine = typeOfMachine;
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

    public boolean hasWarranty(boolean productWarranty) {
        return hasWarranty;
    }

    public void setWarranty(boolean hasWarranty) {
        this.hasWarranty = hasWarranty;
    }

    public int getWarrantyInMonths() {
        return warrantyInMonths;
    }

    public void setWarrantyInMonths(int warrantyInMonths) {
        this.warrantyInMonths = warrantyInMonths;
    }

    public boolean isHasWarranty() {
        return hasWarranty;
    }

    public void setHasWarranty(boolean hasWarranty) {
        this.hasWarranty = hasWarranty;
    }

}
