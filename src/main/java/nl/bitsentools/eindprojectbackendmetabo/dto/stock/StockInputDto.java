package nl.bitsentools.eindprojectbackendmetabo.dto.stock;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.bitsentools.eindprojectbackendmetabo.models.enums.TypeOfMachine;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class StockInputDto {

        @NotBlank(message = "Merknaam moet ingevuld zijn.")
        public String brandName;

        @NotBlank(message = "Productnaam moet ingevuld zijn.")
        public String productName;

        @Min(value = 1, message = "Productnummer moet groter dan 0 zijn.")
        public int productNumber;

        @NotBlank(message = "Type machine mag niet leeg zijn.")
        @Enumerated(EnumType.STRING)
        public TypeOfMachine typeOfMachine;

        @Min(value = 0, message = "Voorraad moet 0 of groter zijn.")
        public int productInStock;

        public LocalDate orderPlacedDate;

        @Min(value = 0, message = "Aantal weken voor levering moet 0 of groter zijn.")
        public int weeksToDelivery;

        @Min(value = 0, message = "Aantalverkochte producten moet minimaal 0 of meer zijn.")
        public int productSold;

        @Min(value= 0, message = "Aantal producten op voorraad moet minimaal 0 of meer zijn.")
        public int quantityInStock;

        public boolean outOfStock;

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

        public TypeOfMachine getTypeOfMachine() {
                return typeOfMachine;
        }

        public void setTypeOfMachine(TypeOfMachine typeOfMachine) {
                this.typeOfMachine = typeOfMachine;
        }

        public int getProductInStock() {
                return productInStock;
        }

        public void setProductInStock(int productInStock) {
                this.productInStock = productInStock;
        }

        public LocalDate getOrderPlacedDate() {
                return orderPlacedDate;
        }

        public void setOrderPlacedDate(LocalDate orderPlacedDate) {
                this.orderPlacedDate = orderPlacedDate;
        }

        public int getWeeksToDelivery() {
                return weeksToDelivery;
        }

        public void setWeeksToDelivery(int weeksToDelivery) {
                this.weeksToDelivery = weeksToDelivery;
        }

        public int getProductSold() {
                return productSold;
        }

        public void setProductSold(int productSold) {
                this.productSold = productSold;
        }

        public int getQuantityInStock() {
                return quantityInStock;
        }

        public void setQuantityInStock(int quantityInStock) {
                this.quantityInStock = quantityInStock;
        }

        public boolean isOutOfStock() {
                return outOfStock;
        }

        public void setOutOfStock(boolean outOfStock) {
                this.outOfStock = outOfStock;
        }
}
