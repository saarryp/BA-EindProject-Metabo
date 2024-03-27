package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductOutputDtoWarranty {

        public Long id;

    @NotBlank(message = "Merknaam is niet ingevoerd.")
    public String brandName;

    @NotBlank(message = "Productnaam is niet ingevoerd")
    public String productName;

    @NotBlank(message = "Het productnummer moet ingevoerd zijn.")
    @NotNull(message = "Het productnummer moet groter dan 0 zijn.")
    public int productNumber;

    @NotNull(message = "De prijs moet zijn ingevoerd.")
    public double price;
    public boolean hasWarranty;

    @NotNull(message = "Garantie heeft een minimum waarde die 0 of groter is.")
    public int warrantyInMonths;

    @NotBlank(message = "Het type machine is niet ingevoerd.")
    @Enumerated(EnumType.STRING)
    public TypeOfMachine typeOfMachine;

    public String defaultImageBase64;



        //default constructor

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
        this.defaultImageBase64 = defaultImageBase64;
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

    public String getDefaultImageBase64() {
        return defaultImageBase64;
    }

    public void setDefaultImageBase64(String defaultImageBase64) {
        this.defaultImageBase64 = defaultImageBase64;
    }
}
