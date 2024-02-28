package nl.bitsentools.eindprojectbackendmetabo.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;

public class ProductOutputDtoWarranty {

        public Long id;
        public String brandName;

        public String productName;

        public int productNumber;

        public double price;

        public boolean hasWarranty;
        public int warrantyInMonths;


        @Enumerated(EnumType.STRING)
        public TypeOfMachine typeOfMachine;

        //default constructor

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


}
